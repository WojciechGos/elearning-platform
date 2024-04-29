package project.backend.courses.lessonResource.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.lessonResource.model.LessonResource;

public interface LessonResourceController {

    ResponseEntity<LessonResource> getLessonResource(@PathVariable("lessonResourceId") Long lessonResourceId);

    ResponseEntity<LessonResource> updateLessonResource(
            @PathVariable("lessonResourceId") Long id,
            @RequestBody LessonResource lessonResource);
    ResponseEntity<Void> deleteLessonResource(@PathVariable("lessonResourceId") Long lessonResourceId);
}
