package project.backend.courses.lesson;

import java.net.URL;

public record LessonRequest(
    String title,
    String description,
    String content,
    int lessonNumber,
    URL videoUrl
) {
}
