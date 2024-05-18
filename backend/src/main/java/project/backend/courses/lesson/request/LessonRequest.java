package project.backend.courses.lesson.request;


public record LessonRequest(
    String title,
    String description,
    String content,
    int lessonNumber,
    String videoUrl
) {
}
