package project.backend.courses.category.service;

import java.security.Principal;

public interface CourseCategoryService {

    void addCategoryToCourse(Long courseId, Long categoryId, Principal principal);

    void removeCategoryFromCourse(Long courseId, Long categoryId, Principal principal);
}
