package project.backend.courses.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.lesson.service.CourseLessonService;
import project.backend.courses.utils.file.response.FileResponse;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/courses/{courseId}/lessons")
public class CourseLessonControllerImpl implements CourseLessonController {

    private final CourseLessonService courseLessonService;

    @Override
    @PostMapping()
    public ResponseEntity<LessonDTO> addLessonToCourse(
            Principal principal,
            @PathVariable("courseId") Long courseId,
            @RequestBody LessonDTO lesson) {

        LessonDTO createdLesson = courseLessonService.addLessonToCourse(courseId, lesson, principal);
        return new ResponseEntity<>(createdLesson, HttpStatus.CREATED);
    }

}
