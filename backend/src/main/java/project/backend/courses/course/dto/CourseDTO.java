package project.backend.courses.course.dto;

import project.backend.courses.course.model.CourseState;
import project.backend.courses.course.model.TargetAudience;
import project.backend.courses.lesson.dto.LessonDTO;

import java.math.BigDecimal;
import java.util.List;

public record CourseDTO(
        Long id,
        String title,
        String description,
        BigDecimal price,
        BigDecimal discountPrice,
        List<String> categories,
        String language,
        Long totalDuration,
        Double rating,
        String imageURL,
        List<LessonDTO> lessons,
        Integer enrollmentCount,
        CourseState courseState,
        TargetAudience targetAudience
) {
}
