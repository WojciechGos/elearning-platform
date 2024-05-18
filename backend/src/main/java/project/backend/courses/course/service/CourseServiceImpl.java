package project.backend.courses.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.dto.FilterCourseDTO;
import project.backend.courses.course.repository.CourseRepository;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.course.repository.CourseSpecification;
import project.backend.exception.types.ResourceNotFoundException;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
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
        return new FilterCourseDTO(count, courseList);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course updatedCourse = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(id)));

        if (course.getTitle() != null) updatedCourse.setTitle(course.getTitle());
        if (course.getDescription() != null) updatedCourse.setDescription(course.getDescription());
        if (course.getPrice() != null) updatedCourse.setPrice(course.getPrice());
        if (course.getLanguage() != null) updatedCourse.setLanguage(course.getLanguage());
        if (course.getEnrollmentCount() != null) updatedCourse.setEnrollmentCount(course.getEnrollmentCount());

        return courseRepository.save(updatedCourse);
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


}
