package project.backend.courses.lesson.dto;


public record LessonDTO(
        Long id,
        String title,
        String description,
        String content,
        int lessonNumber,
        String videoUrl
) {
}
