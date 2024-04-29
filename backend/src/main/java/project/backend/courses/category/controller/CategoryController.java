package project.backend.courses.category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import project.backend.courses.category.model.Category;

import java.util.List;

public interface CategoryController {

    ResponseEntity<List<Category>> getCategories();
    ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId);

}
