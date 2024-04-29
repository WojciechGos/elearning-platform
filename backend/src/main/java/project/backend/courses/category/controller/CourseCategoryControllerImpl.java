package project.backend.courses.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.category.service.CourseCategoryService;

@RestController
@RequestMapping(path = "api/v1/courses/{courseId}/categories/{categoryId}")
@RequiredArgsConstructor
public class CourseCategoryControllerImpl implements CourseCategoryController {

    private final CourseCategoryService courseCategoryService;

    @Override
    @PostMapping
    public ResponseEntity<HttpStatus> addCategoryToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("categoryId") Long categoryId) {
        courseCategoryService.addCategoryToCourse(courseId, categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<HttpStatus> removeCategoryFromCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("categoryId") Long categoryId) {
        courseCategoryService.removeCategoryFromCourse(courseId, categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
