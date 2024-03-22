package project.backend.courses.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
