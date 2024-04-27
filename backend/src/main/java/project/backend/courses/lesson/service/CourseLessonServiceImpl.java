package project.backend.courses.lesson.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import project.backend.courses.course.model.Course;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;
import project.backend.exception.ResourceNotFoundException;

@Service
public class CourseLessonServiceImpl implements CourseLessonService{

    @Transactional
    public Lesson addLessonToCourse(Long courseId, LessonRequest lesson) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id [%s] ".formatted(courseId)));
        Lesson createdLesson = lessonService.createLesson(lesson);
        course.getLessons().add(createdLesson);
        courseRepository.save(course);
        return createdLesson;
    }

}
