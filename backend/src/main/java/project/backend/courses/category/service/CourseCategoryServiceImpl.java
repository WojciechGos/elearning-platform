package project.backend.courses.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.category.model.Category;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;

@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CategoryService categoryService;
    private final CourseService courseService;

    @Override
    public void addCategoryToCourse(Long courseId, Long categoryId) {
        Course course = courseService.getCourseById(courseId);
        Category category = categoryService.getCategory(categoryId);
        course.getCategories().add(category);
        courseService.updateCourse(courseId, course);
    }

    @Override
    public void removeCategoryFromCourse(Long courseId, Long categoryId) {
        Course course = courseService.getCourseById(courseId);
        Category category = categoryService.getCategory(categoryId);
        course.getCategories().remove(category);
        courseService.updateCourse(courseId, course);
    }
}