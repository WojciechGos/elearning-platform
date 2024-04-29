package project.backend.courses.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.category.model.Category;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.model.TargetAudience;
import project.backend.courses.course.service.CourseService;
import project.backend.courses.language.model.Language;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseControllerImpl implements CourseController {
    private final CourseService courseService;

    @Override
    @GetMapping
    public ResponseEntity<List<Course>> getCourses(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<String> category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) List<String> targetAudience,
            @RequestParam(required = false) List<String> language,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false) List<String> fields
    ) {
        List<Course> courses = courseService.getCourses(
                keyword,
                category,
                minPrice,
                maxPrice,
                minRating,
                targetAudience,
                language,
                page,
                limit,
                fields
        );
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable("courseId") Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course newCourse = courseService.createCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @Override
    @PatchMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(courseId, course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
