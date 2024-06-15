package project.backend.courses.course.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.model.Course;
import project.backend.courses.category.model.Category;
import project.backend.courses.lesson.dto.LessonDTO;
import project.backend.courses.lesson.mapper.LessonDTOMapper;
import project.backend.users.user.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseDTOMapper {
    private final LessonDTOMapper lessonDTOMapper;
    private final UserMapper userMapper;
    public CourseDTO toDTO(Course course) {
        Long duration = 0L;
        if (course.getTotalDuration() != null) {
            duration = course.getTotalDuration().toMinutes();
        }

        List<LessonDTO> lessonList = List.of();
        if (course.getLessons() != null) {
            lessonList = course.getLessons().stream().map(lessonDTOMapper::toDTO).collect(Collectors.toList());
        } else {

        }

        List<String> categories = List.of();
        if (course.getCategories() != null) {
            categories = course.getCategories().stream().map(Category::getName).toList();
        }

        String language = "";
        if(course.getLanguage() != null) {
            language = course.getLanguage().getName();
        }

        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getDiscountPrice(),
                categories,
                language,
                duration,
                course.getRating(),
                course.getImageUrl(),
                lessonList,
                course.getEnrollmentCount(),
                course.getCourseState(),
                course.getTargetAudience(),
                userMapper.mapToDTO(course.getAuthor())
        );
    }
}
