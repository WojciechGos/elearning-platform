package project.backend.courses.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import project.backend.courses.course.model.Course;

import java.util.List;

public interface CourseController {

    ResponseEntity<List<Course>> getCourses(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minRating
    );

    ResponseEntity<Course> getCourse(@PathVariable("courseId") Long courseId);

    ResponseEntity<Course> createCourse(@RequestBody Course course);

    ResponseEntity<Course> updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody Course course);

    ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId);

}
