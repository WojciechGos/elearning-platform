package project.backend.courses.course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseService {

    public List<Course> getCourses() {
        return null;
    }

    public Course createCourse(Course course) {
        return null;
    }

    public Course updateCourse(Course course) {
        return null;
    }

    public Course getCourse(Long courseId) {
        return null;
    }

    public void deleteCourse(Long courseId) {
    }

}
