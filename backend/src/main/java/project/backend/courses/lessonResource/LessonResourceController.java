package project.backend.courses.lessonResource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/lesson-resources")
public class LessonResourceController {

    private final LessonResourceService lessonResourceService;


    @GetMapping("/{lessonResourceId}")
    public ResponseEntity<LessonResource> getLessonResource(@PathVariable("lessonResourceId") Long lessonResourceId) {
        return new ResponseEntity<>(lessonResourceService.getLessonResource(lessonResourceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LessonResource> createLessonResource(@RequestBody LessonResource lessonResource) {
        return new ResponseEntity<>(lessonResourceService.createLessonResource(lessonResource), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LessonResource> updateLessonResource(@RequestBody LessonResource lessonResource) {
        return new ResponseEntity<>(lessonResourceService.updateLessonResource(lessonResource), HttpStatus.OK);
    }

    @DeleteMapping("/{lessonResourceId}")
    public ResponseEntity<Void> deleteLessonResource(@PathVariable("lessonResourceId") Long lessonResourceId) {
        lessonResourceService.deleteLessonResource(lessonResourceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
