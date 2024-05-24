package project.backend.courses.lesson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;

public interface CourseLessonController {
    ResponseEntity<LessonDTO> addLessonToCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody LessonDTO lesson);
}
