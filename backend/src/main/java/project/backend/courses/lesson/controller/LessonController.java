package project.backend.courses.lesson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import project.backend.courses.utils.file.request.FileRequest;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.utils.file.response.FileResponse;

import java.util.List;

public interface LessonController {
    ResponseEntity<List<Lesson>> getLessons();

    ResponseEntity<Lesson> getLesson(@PathVariable("lessonId") Long lessonId);

    ResponseEntity<Lesson> updateLesson(Lesson lesson, @PathVariable("lessonId") Long lessonId);

    ResponseEntity<Void> deleteLesson(@PathVariable("lessonId") Long lessonId);
    ResponseEntity<FileResponse> getSignedUrlForUploadLessonVideo(Long lessonId);
    ResponseEntity<String> getSignedUrlForDownloadLessonVideo(Long lessonId);
    ResponseEntity<Void> deleteVideoFromLesson(Long lessonId);
}
