package project.backend.courses.course.dto;

import project.backend.courses.course.model.Course;

import java.util.List;

public record FilterCourseDTO(
        Long count,
        List<Course> courses
) {
}
