package project.backend.courses.lesson.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;

public interface CourseLessonController {
    ResponseEntity<Lesson> addLessonToCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody LessonRequest lesson);
}
