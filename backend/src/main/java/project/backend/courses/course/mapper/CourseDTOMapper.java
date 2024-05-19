package project.backend.courses.course.mapper;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.model.Course;
import project.backend.courses.category.model.Category;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.lesson.mapper.LessonDTOMapper;
import project.backend.courses.lesson.model.Lesson;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseDTOMapper {
    private final LessonDTOMapper lessonDTOMapper;

    public CourseDTO toDTO(Course course) {
        Long duration = 0L;
        if (course.getTotalDuration() != null) {
            duration = course.getTotalDuration().toMinutes();
        }

        List<LessonDTO> lessonList;

        if(course.getLessons() != null) {
            lessonList = course.getLessons().stream().map(lessonDTOMapper::toDTO).collect(Collectors.toList());
        } else {
            lessonList = List.of();
        }

        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getDiscountPrice(),
                course.getCategories().stream().map(Category::getName).toList(),
                course.getLanguage().getName(),
                duration,
                course.getRating(),
                course.getImageURL(),
                lessonList,
                course.getEnrollmentCount(),
                course.getCourseState(),
                course.getTargetAudience()
        );
    }
}
