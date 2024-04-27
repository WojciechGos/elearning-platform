package project.backend.courses.course.service;

import project.backend.courses.course.model.Course;
import project.backend.courses.course.model.CourseState;
import project.backend.exception.ResourceNotFoundException;

import java.util.List;

public interface CourseService {

    Course getCourseById(Long courseId);

    List<Course> getCourses();

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    void deleteCourse(Long courseId);


}
