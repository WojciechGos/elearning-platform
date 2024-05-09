package project.backend.courses.course.service;

import project.backend.courses.course.model.Course;
import project.backend.courses.course.model.FilterCourseDTO;

import java.util.List;

public interface CourseService {

    Course getCourseById(Long courseId);

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

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    void deleteCourse(Long courseId);


}
