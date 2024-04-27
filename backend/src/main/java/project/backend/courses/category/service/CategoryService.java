package project.backend.courses.category.service;

import project.backend.courses.category.model.Category;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategory(Long categoryId);
}
