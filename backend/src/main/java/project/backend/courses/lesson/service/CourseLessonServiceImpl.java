package project.backend.courses.lesson.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.course.mapper.CourseDTOMapper;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.courses.lesson.mapper.LessonDTOMapper;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CourseLessonServiceImpl implements CourseLessonService{

    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseDTOMapper courseDTOMapper;
    private final LessonDTOMapper lessonDTOMapper;

    @Override
    @Transactional
    public LessonDTO addLessonToCourse(Long courseId, LessonDTO lesson, Principal principal) {
        Course course = courseService.getCourseById(courseId);

        Lesson createdLesson = lessonService.createLesson(lesson);

        createdLesson.setCourse(course);

        LessonDTO lessonDTO = lessonDTOMapper.toDTO(createdLesson);

        // assign created lesson to the existing course
        course.getLessons().add(createdLesson);
        // set course state to NULL because otherwise it will trigger insufficient permission error
        course.setCourseState(null);
        courseService.updateCourse(courseId, courseDTOMapper.toDTO(course), principal);

        return lessonDTO;
    }

}
