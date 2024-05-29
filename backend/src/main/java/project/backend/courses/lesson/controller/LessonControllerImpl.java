package project.backend.courses.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.utils.file.request.FileRequest;
import project.backend.courses.lesson.service.LessonServiceImpl;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lessonResource.model.LessonResource;
import project.backend.courses.utils.file.response.FileResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/lessons")
public class LessonControllerImpl implements LessonController {
    private final LessonServiceImpl lessonService;

    @Override
    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons() {
        return new ResponseEntity<>(lessonService.getLessons(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLesson(
            @PathVariable("lessonId") Long lessonId
    ) {
        return new ResponseEntity<>(lessonService.getLesson(lessonId), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(Lesson lesson, @PathVariable("lessonId") Long lessonId) {
        return new ResponseEntity<>(lessonService.updateLesson(lesson), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("lessonId") Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{lessonId}/lesson-resources")
    public ResponseEntity<LessonResource> addLessonResourceToLesson(
            @PathVariable("lessonId") Long lessonId,
            @RequestBody LessonResource lessonResource) {
        LessonResource createdLessonResource = lessonService.addLessonResourceToLesson(lessonId, lessonResource);
        return new ResponseEntity<>(createdLessonResource, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{lessonId}/video/upload")
    public ResponseEntity<FileResponse> getSignedUrlForUploadLessonVideo(
            @PathVariable("lessonId") Long lessonId) {
        return new ResponseEntity<>(lessonService.getSignedUrlForUploadLessonVideo(lessonId), HttpStatus.OK);
    }


    @Override
    @GetMapping("/{lessonId}/video/download")
    public ResponseEntity<String> getSignedUrlForDownloadLessonVideo(@PathVariable("lessonId") Long lessonId) {
        return new ResponseEntity<>(lessonService.getSignedUrlForDownloadLessonVideo(lessonId), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{lessonId}/video")
    public ResponseEntity<Void> deleteVideoFromLesson(Long lessonId) {
        lessonService.deleteVideoFromLesson(lessonId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
