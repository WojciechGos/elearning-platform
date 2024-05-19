package project.backend.courses.lesson.mapper;

import org.springframework.stereotype.Service;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.lesson.model.Lesson;

@Service
public class LessonDTOMapper {
    public LessonDTO toDTO(Lesson lesson) {
        return new LessonDTO(
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getContent(),
                lesson.getLessonNumber(),
                lesson.getVideoUrl()
        );
    }

}
