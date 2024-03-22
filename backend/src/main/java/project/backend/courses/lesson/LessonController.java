package project.backend.courses.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/lessons")
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons() {
        return new ResponseEntity<>(lessonService.getLessons(), HttpStatus.OK);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLesson(@PathVariable("lessonId") Long lessonId) {
        return new ResponseEntity<>(lessonService.getLesson(lessonId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(Lesson lesson) {
        return new ResponseEntity<>(lessonService.createLesson(lesson), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Lesson> updateLesson(Lesson lesson) {
        return new ResponseEntity<>(lessonService.updateLesson(lesson), HttpStatus.OK);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("lessonId") Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
