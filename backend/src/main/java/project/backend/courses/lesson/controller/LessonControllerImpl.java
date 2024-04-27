package project.backend.courses.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.lesson.service.LessonServiceImpl;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lessonResource.model.LessonResource;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/lessons")
public class LessonControllerImpl {
    private final LessonServiceImpl lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons() {
        return new ResponseEntity<>(lessonService.getLessons(), HttpStatus.OK);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLesson(@PathVariable("lessonId") Long lessonId) {
        return new ResponseEntity<>(lessonService.getLesson(lessonId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Lesson> updateLesson(Lesson lesson) {
        return new ResponseEntity<>(lessonService.updateLesson(lesson), HttpStatus.OK);
    }

    @PostMapping("/{lessonId}/lesson-resources")
    public ResponseEntity<LessonResource> addLessonResourceToLesson(
            @PathVariable("lessonId") Long lessonId,
            @RequestBody LessonResource lessonResource)
    {
        LessonResource createdLessonResource = lessonService.addLessonResourceToLesson(lessonId,lessonResource);
        return new ResponseEntity<>(createdLessonResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("lessonId") Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
