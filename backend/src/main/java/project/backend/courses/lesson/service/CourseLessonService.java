package project.backend.courses.lesson.service;

import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;

public interface CourseLessonService {
    LessonDTO addLessonToCourse(Long courseId, LessonDTO lesson);
}
