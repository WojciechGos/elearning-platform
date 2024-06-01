package project.backend.courses.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

public interface CourseCategoryController {

    ResponseEntity<HttpStatus> addCategoryToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("categoryId") Long categoryId,
            Principal principal);


    ResponseEntity<HttpStatus> removeCategoryFromCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("categoryId") Long categoryId,
            Principal principal);
}
