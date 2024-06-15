package project.backend.courses.course.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import project.backend.carts.cart.model.CartStatus;
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
import project.backend.permission.service.PermissionService;
import project.backend.exception.types.ForbiddenException;
import project.backend.exception.types.ResourceNotFoundException;
import project.backend.user.UserService;
import project.backend.user.User;

import org.springframework.data.domain.Pageable;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;
    private final LanguageService languageService;
    private final CourseDTOMapper courseDTOMapper;
    private final UserService userService;
    private final FileService fileService;
    private final LessonService lessonService;
    private final LessonDTOMapper lessonDTOMapper;
    private final PermissionService permissionService;

    @Value("${aws.s3.url}")
    private String awsS3Url;

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findCourseWithSortedLessonsByIdOrderByLessonsLessonNumber(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
    }

    @Override
    public CourseDTO getCourseDTOById(Long courseId) {
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
        if (principal.getName() == null) {
            throw new ForbiddenException("You need to be logged in to create a course.");
        }

        Lesson lesson = Lesson.builder()
                .lessonNumber(1)
                .title("")
                .description("")
                .videoUrl("")
                .build();
        Lesson newLesson = lessonService.createLesson(lessonDTOMapper.toDTO(lesson));

        Course newCourse = Course.builder()
                .title("")
                .description("")
                .price(new BigDecimal(0))
                .discountPrice(new BigDecimal(0))
                .language(null)
                .categories(null)
                .rating(0)
                .targetAudience(null)
                .courseState(CourseState.CREATING)
                .enrollmentCount(0)
                .lessons(List.of(newLesson))
                .author(userService.getUserByEmail(principal.getName()))
                .build();

        return courseDTOMapper.toDTO(courseRepository.save(newCourse));
    }

    @Override
    public CourseDTO updateCourse(Long courseId, CourseDTO course, Principal principal) {
        Course updatedCourse = getCourseById(courseId);

        if (!permissionService.hasPermissionToEditCourse(updatedCourse, principal)) {
            throw new ForbiddenException("Insufficient role: You can only update your own courses.");
        }

        if (course.title() != null) updatedCourse.setTitle(course.title());
        if (course.description() != null) updatedCourse.setDescription(course.description());
        if (course.price() != null) updatedCourse.setPrice(course.price());
        if (course.targetAudience() != null) updatedCourse.setTargetAudience(course.targetAudience());
        if (course.language() != null) {
            if (!course.language().isEmpty() || !course.language().isBlank()) {
                Language language = languageService.getLanguageByName(course.language());
                updatedCourse.setLanguage(language);
            }
        }

        if (course.courseState() != null) {
            if (permissionService.hasRole(principal, "ROLE_USER")) {
                if (course.courseState() == CourseState.READY_TO_ACCEPT || course.courseState() == CourseState.HIDDEN || course.courseState() == CourseState.CREATING) {
                    updatedCourse.setCourseState(course.courseState());
                } else {
                    throw new ForbiddenException("Insufficient role: You can only change course state to READY_TO_ACCEPT or HIDDEN.");
                }
            } else if (permissionService.hasRole(principal, "ROLE_ADMIN")) {
                updatedCourse.setCourseState(course.courseState());
            }
        }
        return courseDTOMapper.toDTO(courseRepository.save(updatedCourse));
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
        course.setCourseState(CourseState.HIDDEN);
        courseRepository.save(course);
    }

    @Override
    public List<CourseDTO> getUsersCourse(CourseState courseState, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        List<Course> courses;
        if (courseState != null) {
            courses = user.getCompletedCourses().stream()
                    .filter(course -> course.getCourseState() == courseState)
                    .collect(Collectors.toList());
        } else {
            courses = user.getCompletedCourses();
        }

        List<CourseDTO> courseDTOs = courses.stream().map(courseDTOMapper::toDTO).toList();
        logger.info("CourseDTOs for user {} with state {}: {}", principal.getName(), courseState, courseDTOs);
        return courseDTOs;
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
    public CourseDTO completeCourse(Long courseId, Principal principal) {
        Course course = getCourseById(courseId);
        User user = userService.getUserByEmail(principal.getName());

        boolean hasPurchased = user.getCarts().stream()
                .filter(cart -> cart.getCartStatus() == CartStatus.COMPLETED)
                .flatMap(cart -> cart.getItems().stream())
                .anyMatch(cartItem -> cartItem.getCourse().equals(course));

        if (!hasPurchased) {
            throw new ForbiddenException("You have not purchased this course.");
        }

        course.setCourseState(CourseState.COMPLETED);
        user.getCompletedCourses().add(course);
        userService.save(user);

        Course savedCourse = courseRepository.save(course);
        logger.info("Course {} has been marked as COMPLETED for user {}", course.getId(), user.getEmail());
        return courseDTOMapper.toDTO(savedCourse);
    }

    @Override
    public byte[] generateCertificate(Long courseId, Principal principal) {
        Course course = getCourseById(courseId);
        User user = userService.getUserByEmail(principal.getName());

        boolean hasCompleted = user.getCompletedCourses().contains(course);
        if (!hasCompleted) {
            throw new ForbiddenException("You have not completed this course.");
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);
            Font subTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Font bodyFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);

            Paragraph title = new Paragraph("Certificate of Completion", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(50);
            document.add(title);

            Paragraph subTitle = new Paragraph("This is to certify that", subTitleFont);
            subTitle.setAlignment(Element.ALIGN_CENTER);
            subTitle.setSpacingAfter(30);
            document.add(subTitle);

            Paragraph name = new Paragraph(user.getFirstName() + " " + user.getLastName(), subTitleFont);
            name.setAlignment(Element.ALIGN_CENTER);
            name.setSpacingAfter(30);
            document.add(name);

            Paragraph courseTitle = new Paragraph("has successfully completed the course", bodyFont);
            courseTitle.setAlignment(Element.ALIGN_CENTER);
            courseTitle.setSpacingAfter(20);
            document.add(courseTitle);

            Paragraph courseName = new Paragraph(course.getTitle(), bodyFont);
            courseName.setAlignment(Element.ALIGN_CENTER);
            courseName.setSpacingAfter(50);
            document.add(courseName);

            LineSeparator separator = new LineSeparator();
            separator.setLineColor(BaseColor.BLACK);
            separator.setPercentage(80);
            separator.setAlignment(Element.ALIGN_CENTER);
            document.add(separator);
            
            Paragraph footer = new Paragraph("Congratulations!", subTitleFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(50);
            document.add(footer);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error generating PDF", e);
        }

        return out.toByteArray();
    }

}
