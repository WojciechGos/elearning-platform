package project.backend.courses.course.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.category.model.Category;
import project.backend.courses.category.service.CategoryService;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.repository.CourseRepository;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;
import project.backend.courses.lesson.service.LessonServiceImpl;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LessonServiceImpl lessonService;
    private final CategoryService categoryService;
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
    }


    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course updatedCourse = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(id)));

        if(course.getTitle() != null) updatedCourse.setTitle(course.getTitle());
        if(course.getDescription() != null) updatedCourse.setDescription(course.getDescription());
        if(course.getPrice() != null) updatedCourse.setPrice(course.getPrice());
        if(course.getLanguage() != null) updatedCourse.setLanguage(course.getLanguage());

        return courseRepository.save(updatedCourse);
    }


    public void deleteCourse(Long courseId) {
        /*
            IRL it would be better to send request to administration to review deletion request of the course,
            because some users may have already bought it.
            However, for sake of simplicity we will just hide it
         */
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
        course.setCourseState(CourseState.HIDDEN);
        courseRepository.save(course);
    }



    public void addCategoryToCourse(Long courseId, Long categoryId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
        Category category = categoryService.getCategory(categoryId);
        course.getCategories().add(category);
        courseRepository.save(course);
    }

    public void removeCategoryFromCourse(Long courseId, Long categoryId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
        Category category = categoryService.getCategory(categoryId);
        course.getCategories().remove(category);
        courseRepository.save(course);
    }
}
