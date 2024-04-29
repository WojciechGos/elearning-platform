package project.backend.courses.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CourseCategoryController {

     ResponseEntity<HttpStatus> addCategoryToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("categoryId") Long categoryId);


    ResponseEntity<HttpStatus> removeCategoryFromCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("categoryId") Long categoryId);
}
