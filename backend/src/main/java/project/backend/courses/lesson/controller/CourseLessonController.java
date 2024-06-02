package project.backend.courses.lesson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.utils.file.request.FileRequest;
import project.backend.courses.utils.file.response.FileResponse;

import java.security.Principal;

public interface CourseLessonController {
    ResponseEntity<LessonDTO> addLessonToCourse(
            Principal principal,
            @PathVariable("courseId") Long courseId,
            @RequestBody LessonDTO lesson);
}
