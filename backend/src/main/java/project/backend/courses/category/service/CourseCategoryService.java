package project.backend.courses.category.service;

import project.backend.courses.category.model.Category;
import project.backend.courses.course.model.Course;
import project.backend.exception.ResourceNotFoundException;

public interface CourseCategoryService {

    void addCategoryToCourse(Long courseId, Long categoryId);

    void removeCategoryFromCourse(Long courseId, Long categoryId);
}
