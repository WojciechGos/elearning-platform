package project.backend.courses.lesson.service;

import project.backend.courses.course.model.Course;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;
import project.backend.exception.ResourceNotFoundException;

public interface CourseLessonService {
    Lesson addLessonToCourse(Long courseId, LessonRequest lesson);
}
