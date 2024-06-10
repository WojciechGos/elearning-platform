package project.backend.courses.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.category.model.Category;
import project.backend.courses.course.mapper.CourseDTOMapper;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.exception.types.BadRequestException;
import project.backend.permission.service.PermissionService;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CategoryService categoryService;
    private final CourseService courseService;
    private final CourseDTOMapper courseDTOMapper;
    private final PermissionService permissionService;

    @Override
    public void addCategoryToCourse(Long courseId, Long categoryId, Principal principal) {

        Course course = courseService.getCourseById(courseId);

        if(permissionService.hasPermissionToEditCourse(course, principal)) {
            throw new BadRequestException("User does not have permission to edit course");
        }

        List<Category> existingCategory = course.getCategories().stream().filter(c -> c.getId().equals(categoryId)).toList();
        if(!existingCategory.isEmpty()) {
            throw new BadRequestException("Category already exists in course");
        }

        Category category = categoryService.getCategory(categoryId);
        course.getCategories().add(category);
        courseService.updateCourse(courseId, courseDTOMapper.toDTO(course), principal);
    }

    @Override
    public void removeCategoryFromCourse(Long courseId, Long categoryId, Principal principal) {
        Course course = courseService.getCourseById(courseId);

        if(permissionService.hasPermissionToEditCourse(course, principal)) {
            throw new BadRequestException("User does not have permission to edit course");
        }

        Category category = categoryService.getCategory(categoryId);
        course.getCategories().remove(category);
        courseService.updateCourse(courseId, courseDTOMapper.toDTO(course), principal);
    }
}
