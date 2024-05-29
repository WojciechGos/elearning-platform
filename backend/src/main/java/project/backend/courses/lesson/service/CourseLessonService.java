package project.backend.courses.lesson.service;

import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.utils.file.request.FileRequest;
import project.backend.courses.utils.file.response.FileResponse;

public interface CourseLessonService {
    LessonDTO addLessonToCourse(Long courseId, LessonDTO lesson);

}
