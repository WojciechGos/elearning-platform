package project.backend.courses.comment.dto;

public record CommentDTO(
        Long id,
        String content,
        Long courseId,
        Long authorId
) {
}
