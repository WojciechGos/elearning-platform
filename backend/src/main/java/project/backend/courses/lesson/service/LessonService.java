package project.backend.courses.lesson.service;

import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.utils.file.response.FileResponse;

import java.util.List;

public interface LessonService {

    List<Lesson> getLessons();
    Lesson getLesson(Long lessonId);

    Lesson createLesson(LessonDTO lesson);

    LessonDTO updateLesson(Long lessonId, LessonDTO lesson);

    void deleteLesson(Long lessonId);

    String getSignedUrlForDownloadLessonVideo(Long lessonId);
    FileResponse getSignedUrlForUploadLessonVideo(Long lessonId);

    void deleteVideoFromLesson(Long lessonId);

}
