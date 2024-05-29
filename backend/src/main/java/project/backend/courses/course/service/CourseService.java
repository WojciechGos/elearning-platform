package project.backend.courses.course.service;

import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.dto.FilterCourseDTO;

import java.security.Principal;
import java.util.List;

public interface CourseService {

    Course getCourseById(Long courseId);

    CourseDTO getCourseDTOById(Long courseId);

    FilterCourseDTO getCourses(
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
    );

    CourseDTO createCourse(CourseDTO course);

    CourseDTO updateCourse(Long id, CourseDTO course, Principal principal);

    void deleteCourse(Long courseId);

}
