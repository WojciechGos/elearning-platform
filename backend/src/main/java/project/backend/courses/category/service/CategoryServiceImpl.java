package project.backend.courses.category.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.category.repository.CategoryRepository;
import project.backend.courses.category.model.Category;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

        private final CategoryRepository categoryRepository;
        public List<Category> getCategories() {
            return categoryRepository.findAll();
        }
        public Category getCategory(Long categoryId) {
            return categoryRepository.findById(categoryId).orElseThrow(() ->
                    new ResourceNotFoundException("Category not found with id [%s] ".formatted(categoryId)));
        }


}
