package project.backend.courses.lesson.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.course.mapper.CourseDTOMapper;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;

@Service
@RequiredArgsConstructor
public class CourseLessonServiceImpl implements CourseLessonService{

    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseDTOMapper courseDTOMapper;

    @Override
    @Transactional
    public Lesson addLessonToCourse(Long courseId, LessonDTO lesson) {
        Course course = courseService.getCourseById(courseId);
        Lesson createdLesson = lessonService.createLesson(lesson);
        course.getLessons().add(createdLesson);
        courseService.updateCourse(courseId, courseDTOMapper.toDTO(course));
        return createdLesson;
    }

}
