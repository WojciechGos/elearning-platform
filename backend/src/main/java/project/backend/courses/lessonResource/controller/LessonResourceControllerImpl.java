package project.backend.courses.lessonResource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.lessonResource.service.LessonResourceServiceImpl;
import project.backend.courses.lessonResource.model.LessonResource;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/lesson-resources")
public class LessonResourceControllerImpl implements LessonResourceController {

    private final LessonResourceServiceImpl lessonResourceService;


    @GetMapping("/{lessonResourceId}")
    public ResponseEntity<LessonResource> getLessonResource(@PathVariable("lessonResourceId") Long lessonResourceId) {
        return new ResponseEntity<>(lessonResourceService.getLessonResource(lessonResourceId), HttpStatus.OK);
    }


    @PatchMapping("/{lessonResourceId}")
    public ResponseEntity<LessonResource> updateLessonResource(
            @PathVariable("lessonResourceId") Long id,
            @RequestBody LessonResource lessonResource)
    {
        return new ResponseEntity<>(lessonResourceService.updateLessonResource(id, lessonResource), HttpStatus.OK);
    }

    @DeleteMapping("/{lessonResourceId}")
    public ResponseEntity<Void> deleteLessonResource(@PathVariable("lessonResourceId") Long lessonResourceId) {
        lessonResourceService.deleteLessonResource(lessonResourceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
