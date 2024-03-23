package project.backend.courses.course;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.courses.language.Language;
import project.backend.courses.language.LanguageService;
import project.backend.courses.lesson.LessonCommandLineRunner;
import project.backend.courses.lesson.LessonService;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
@Order(20)
public class CourseCommandLineRunner implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final LanguageService languageService;

    @Override
    public void run(String... args) throws Exception {

        List<Language> languages = languageService.getLanguages();

        courseRepository.save(new Course("Java Programming","Learn Java programming language", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(0), 1001, CourseState.PUBLISHED));
        courseRepository.save(new Course("Business basic concepts","Learn business basic concepts", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(1), 200, CourseState.PUBLISHED));
        courseRepository.save(new Course("Python Programming","Learn Python programming language", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(2), 200, CourseState.PUBLISHED));
        courseRepository.save(new Course("Data Science","Learn data science and analytics", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(3), 300, CourseState.PUBLISHED));
        courseRepository.save(new Course("Web Development","Learn web development technologies", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(4), 400, CourseState.HIDDEN));
        courseRepository.save(new Course("Mobile App Development","Learn mobile app development", new BigDecimal(100), languages.get(2), null, LessonCommandLineRunner.getLessonsPack(5), 40, CourseState.PUBLISHED));
        courseRepository.save(new Course("Artificial Intelligence","Learn artificial intelligence and machine learning", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(6), 70, CourseState.PUBLISHED));
        courseRepository.save(new Course("Cloud Computing","Learn cloud computing services and technologies", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(7), 90, CourseState.PUBLISHED));
        courseRepository.save(new Course("Cybersecurity","Learn cybersecurity and information security", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(8), 60, CourseState.PUBLISHED));
        courseRepository.save(new Course("UI/UX Design","Learn user interface and user experience design", new BigDecimal(100), languages.get(1), null, LessonCommandLineRunner.getLessonsPack(9), 231, CourseState.PUBLISHED));
        courseRepository.save(new Course("Java advance","Learn software development", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(11), 3, CourseState.PUBLISHED));
        courseRepository.save(new Course("Business Management","Learn business management", new BigDecimal(100), languages.get(0), null, LessonCommandLineRunner.getLessonsPack(10), 983, CourseState.PUBLISHED));
    }
}
