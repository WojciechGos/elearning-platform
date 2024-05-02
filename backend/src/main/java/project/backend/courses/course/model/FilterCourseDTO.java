package project.backend.courses.course.model;

import java.util.List;

public record FilterCourseDTO(
        Long count,
        List<Course> courses
) {
}
