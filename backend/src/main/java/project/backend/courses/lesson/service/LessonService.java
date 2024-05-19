package project.backend.courses.lesson.service;

import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;

import java.util.List;

public interface LessonService {

    List<Lesson> getLessons();
    Lesson getLesson(Long lessonId);

    Lesson createLesson(LessonDTO lesson);

    Lesson updateLesson(Lesson lesson);

    void deleteLesson(Long lessonId);

}
