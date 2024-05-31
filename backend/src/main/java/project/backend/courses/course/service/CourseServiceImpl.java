package project.backend.courses.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.mapper.CourseDTOMapper;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.dto.FilterCourseDTO;
import project.backend.courses.course.repository.CourseRepository;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.course.repository.CourseSpecification;
import project.backend.courses.language.model.Language;
import project.backend.courses.language.service.LanguageService;
import project.backend.courses.lesson.mapper.LessonDTOMapper;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.service.LessonService;
import project.backend.courses.utils.file.service.FileService;
import project.backend.exception.types.ForbiddenException;
import project.backend.exception.types.ResourceNotFoundException;

import org.springframework.data.domain.Pageable;
import project.backend.user.User;
import project.backend.user.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LanguageService languageService;
    private final CourseDTOMapper courseDTOMapper;
    private final UserService userService;
    private final FileService fileService;
    private final LessonService lessonService;
    private final LessonDTOMapper lessonDTOMapper;
    @Value("${aws.s3.url}")
    private String awsS3Url;

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
    }

    @Override
    public CourseDTO getCourseDTOById(Long courseId) {

        // if user nie ma uprawnien to throw ForbiddenException

        return courseDTOMapper.toDTO(getCourseById(courseId));
    }

    @Override
    public FilterCourseDTO getCourses(
            String keyword,
            List<String> category,
            Double minPrice,
            Double maxPrice,
            Double minRating,
            List<String> targetAudience,
            List<String> language,
            Integer page,
            Integer limit,
            List<String> fields
    ) {
        Specification<Course> spec = Specification
                .where(CourseSpecification.hasKeyword(keyword))
                .and(CourseSpecification.hasCategory(category))
                .and(CourseSpecification.priceBetween(minPrice, maxPrice))
                .and(CourseSpecification.minRating(minRating))
                .and(CourseSpecification.hasTargetAudience(targetAudience))
                .and(CourseSpecification.hasLanguage(language));

        Pageable pageable = PageRequest.of(page, limit);
        Long count = courseRepository.count(spec);
        List<Course> courseList = courseRepository.findAll(spec, pageable).getContent();
        List<CourseDTO> courseDTOList = courseList.stream().map(courseDTOMapper::toDTO).toList();
        return new FilterCourseDTO(count, courseDTOList);
    }

    @Override
    public CourseDTO createCourse(@Validated CourseDTO course, Principal principal) {

        if(principal.getName() == null)
            throw new ForbiddenException("You need to be logged in to create a course.");

        Lesson lesson = Lesson.builder()
                .title("")
                .description("")
                .videoUrl("")
                .build();
        Lesson newLesson = lessonService.createLesson(lessonDTOMapper.toDTO(lesson));

        Course newCourse = Course.builder()
                .title(course.title())
                .description(course.description())
                .price(course.price())
                .discountPrice(new BigDecimal(0))
                .language(null)
                .categories(null)
                .rating(0)
                .imageUrl(course.imageUrl())
                .targetAudience(course.targetAudience())
                .courseState(CourseState.CREATING)
                .enrollmentCount(0)
                .lessons(List.of(newLesson))
                .author(userService.getUserByEmail(principal.getName()))
                .build();

        return courseDTOMapper.toDTO(courseRepository.save(newCourse));
    }

    @Override
    @PreAuthorize("@courseServiceImpl.isAuthor(#courseId, #principal)")
    public CourseDTO updateCourse(Long courseId, CourseDTO course, Principal principal) {
        Course updatedCourse = getCourseById(courseId);
        User user = userService.getUserByEmail(principal.getName());


        if(user.getAuthorities() == null){
            throw new ForbiddenException("You need to be logged in to save course data.");
        }

        // if role is user allow only author of the course to update it
        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            if (!updatedCourse.getAuthor().getEmail().equals(user.getEmail())) {
                throw new ForbiddenException("Insufficient role: You can only update your own courses.");
            }
        }


        if (course.title() != null) updatedCourse.setTitle(course.title());
        if (course.description() != null) updatedCourse.setDescription(course.description());
        if (course.price() != null) updatedCourse.setPrice(course.price());
        if (course.language() != null) {
            if (!course.language().isEmpty() || !course.language().isBlank()) {
                Language language = languageService.getLanguageByName(course.language());
                updatedCourse.setLanguage(language);
            }
        }


        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            if (course.courseState() != null) {
                if (course.courseState() == CourseState.READY_TO_ACCEPT || course.courseState() == CourseState.HIDDEN || course.courseState() == CourseState.CREATING) {
                    updatedCourse.setCourseState(course.courseState());
                } else {
                    throw new ForbiddenException("Insufficient role: You can only change course state to READY_TO_ACCEPT or HIDDEN.");
                }
            }
        }

        if (user.getAuthorities() != null) {
            if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                if (course.courseState() != null) {
                    updatedCourse.setCourseState(course.courseState());
                }
            }
        }
        return courseDTOMapper.toDTO(courseRepository.save(updatedCourse));
    }

    @Override
    public void deleteCourse(Long courseId) {
        /*
            IRL it would be better to send request to administration to review deletion request of the course,
            because some users may have already bought it.
            However, for sake of simplicity we will just hide it
         */
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
        course.setCourseState(CourseState.HIDDEN);
        courseRepository.save(course);
    }

    @Override
    public List<CourseDTO> getUsersCourse(String courseState, Principal principal) {
        List<Course> filteredCourses = courseRepository.findCoursesByAuthorEmail(principal.getName());
        if(courseState != null && !courseState.isEmpty() && !courseState.isBlank()){
            filteredCourses = filteredCourses.stream().filter(course -> course.getCourseState().toString().equals(courseState)).toList();
        }

        return filteredCourses.stream().map(courseDTOMapper::toDTO).toList();
    }

    @Override
    public String getSignedUrlForImageUpload(Long courseId) {
        Course course = getCourseById(courseId);

        String fileName = "public/courses/" + courseId + "/" + UUID.randomUUID().toString();
        course.setImageUrl(awsS3Url + "/" + fileName);
        courseRepository.save(course);

        return fileService.generateUploadUrl(fileName, "image/png, image/jpeg, image/jpg");
    }

    @Override
    public void deleteCourseImage(Long courseId) {
        Course course = getCourseById(courseId);
        fileService.deleteFile(course.getImageUrl());
        course.setImageUrl(null);
        courseRepository.save(course);
    }

    @Override
    public boolean isAuthor(Long courseId, Principal principal) {
        Course course = getCourseById(courseId);
        return course.getAuthor().getEmail().equals(principal.getName());
    }


}
