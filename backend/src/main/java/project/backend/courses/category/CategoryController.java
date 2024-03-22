package project.backend.courses.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public ResponseEntity<List<Category>> getCategories() {
        return null;
    }

    public ResponseEntity<Category> getCategory(Long categoryId) {
        return null;
    }

}
