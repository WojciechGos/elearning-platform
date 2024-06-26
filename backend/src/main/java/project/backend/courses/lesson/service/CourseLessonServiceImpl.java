package project.backend.courses.lesson.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.mapper.CourseDTOMapper;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.course.service.CourseService;
import project.backend.courses.lesson.mapper.LessonDTOMapper;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.notification.service.NotificationService;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CourseLessonServiceImpl implements CourseLessonService{

    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseDTOMapper courseDTOMapper;
    private final LessonDTOMapper lessonDTOMapper;
    private final NotificationService notificationService;
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
        CourseDTO courseDTO = courseDTOMapper.toDTOWithCourseState(course, null);

        if(course.getCourseState() == CourseState.PUBLISHED)
            notificationService.assignNotifications("Course [%s], has uploaded new lesson. Check it out!".formatted(course.getTitle()), course.getId());


        courseService.updateCourse(courseId, courseDTO, principal);

        return lessonDTO;
    }

}
