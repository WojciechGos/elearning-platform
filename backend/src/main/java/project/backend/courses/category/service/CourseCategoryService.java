package project.backend.courses.category.service;

public interface CourseCategoryService {

    void addCategoryToCourse(Long courseId, Long categoryId);

    void removeCategoryFromCourse(Long courseId, Long categoryId);
}
