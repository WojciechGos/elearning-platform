package project.backend.courses.lesson.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.model.LessonRequest;
import project.backend.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CourseLessonServiceImpl implements CourseLessonService{

    private final CourseService courseService;
    private final LessonService lessonService;

    @Transactional
    public Lesson addLessonToCourse(Long courseId, LessonRequest lesson) {
        Course course = courseService.getCourseById(courseId);
        Lesson createdLesson = lessonService.createLesson(lesson);
        course.getLessons().add(createdLesson);
        courseService.updateCourse(courseId, course);
        return createdLesson;
    }

}
