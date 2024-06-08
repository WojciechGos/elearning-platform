package project.backend.courses.comment.dto;

import java.time.LocalDateTime;

public record CommentDTO(
        Long id,
        String content,
        String authorFirstName,
        String authorLastName,
        LocalDateTime createdDate,
        boolean isCourseAuthor
) {
}
