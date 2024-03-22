package project.backend.courses.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
    }


    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return null;
    }


    public void deleteCourse(Long courseId) {
    }

}
