package project.backend.courses.lesson.service;

import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;

public interface CourseLessonService {
    Lesson addLessonToCourse(Long courseId, LessonRequest lesson);
}
