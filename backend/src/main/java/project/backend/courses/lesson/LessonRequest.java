package project.backend.courses.lesson;


public record LessonRequest(
    String title,
    String description,
    String content,
    int lessonNumber,
    String videoUrl
) {
}
