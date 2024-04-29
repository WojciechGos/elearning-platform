package project.backend.courses.lesson.model;


public record LessonRequest(
    String title,
    String description,
    String content,
    int lessonNumber,
    String videoUrl
) {
}
