package project.backend.courses.course.dto;

import lombok.Builder;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.course.model.TargetAudience;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.user.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
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
        String imageUrl,
        List<LessonDTO> lessons,
        Integer enrollmentCount,
        CourseState courseState,
        TargetAudience targetAudience,
        UserDTO author,
        LocalDateTime updatedOn
) {
}
