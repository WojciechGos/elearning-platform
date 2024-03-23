package project.backend.courses.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.lesson.Lesson;
import project.backend.courses.lesson.LessonRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable("courseId") Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course newCourse = courseService.createCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }


    @PatchMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody Course course)
    {
        Course updatedCourse = courseService.updateCourse(courseId,course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{courseId}/lessons")
    public ResponseEntity<Lesson> addLessonToCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody LessonRequest lesson)
    {
        Lesson createdLesson = courseService.addLessonToCourse(courseId,lesson);
        return new ResponseEntity<>(createdLesson, HttpStatus.CREATED);
    }

    //TODO: Implement the following methods
    @PostMapping("/{courseId}/categories}")
    public ResponseEntity<Course> addCategoryToCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody Long categoryId)
    {
        Course course = courseService.addCategoryToCourse(courseId,categoryId);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    // TODO: Implement the following methods
    @DeleteMapping("/{courseId}/categories/categoryId}")
    public ResponseEntity<HttpStatus> removeCategoryFromCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("categoryId") Long categoryId)
    {
        courseService.removeCategoryFromCourse(courseId,categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
