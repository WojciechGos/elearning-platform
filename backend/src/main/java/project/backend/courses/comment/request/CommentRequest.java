package project.backend.courses.comment.request;

public record CommentRequest(
        String content,
        Long courseId
) {
}
