package project.backend.courses.category;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CategoryService {

        private final CategoryRepository categoryRepository;
        public List<Category> getCategories() {
            return categoryRepository.findAll();
        }
        public Category getCategory(Long categoryId) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            return null;
        }


}
