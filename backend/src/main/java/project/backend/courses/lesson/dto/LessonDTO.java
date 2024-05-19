package project.backend.courses.lesson.dto;


public record LessonDTO(
    String title,
    String description,
    String content,
    int lessonNumber,
    String videoUrl
) {
}
