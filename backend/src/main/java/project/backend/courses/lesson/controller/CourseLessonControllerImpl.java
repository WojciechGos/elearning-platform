package project.backend.courses.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;
import project.backend.courses.lesson.service.CourseLessonService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/course/{courseId}/lessons")
public class CourseLessonControllerImpl implements CourseLessonController {

    private final CourseLessonService courseLessonService;

    @Override
    @PostMapping()
    public ResponseEntity<Lesson> addLessonToCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody LessonRequest lesson) {
        Lesson createdLesson = courseLessonService.addLessonToCourse(courseId, lesson);
        return new ResponseEntity<>(createdLesson, HttpStatus.CREATED);
    }


}
