package project.backend.courses.course;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.courses.category.Category;
import project.backend.courses.category.CategoryService;
import project.backend.courses.language.Language;
import project.backend.courses.language.LanguageService;
import project.backend.courses.lesson.LessonCommandLineRunner;
import project.backend.courses.lesson.LessonService;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Component
@Order(20)
public class CourseCommandLineRunner implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final LanguageService languageService;
    private final LessonService lessonService;
    private final CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {

        List<Language> languages = languageService.getLanguages();
        List<Category> categories = categoryService.getCategories();

        courseRepository.save(Course.builder()
                .title("Java Programming")
                .description("Learn Java programming language")
                .price(new BigDecimal(600))
                .categories(List.of(categories.get(0)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(0))
                .enrollmentCount(1001)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Business basic concepts")
                .description("Learn business basic concepts")
                .price(new BigDecimal(400))
                .discountPrice(new BigDecimal("219.99"))
                .categories(List.of(categories.get(1)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(1))
                .enrollmentCount(200)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Python Programming")
                .description("Learn Python programming language")
                .price(new BigDecimal(530))
                .categories(List.of(categories.get(2)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(2))
                .enrollmentCount(200)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Data Science")
                .description("Learn data science and analytics")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(3)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(3))
                .enrollmentCount(300)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Web Development")
                .description("Learn web development technologies")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(4)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(4))
                .enrollmentCount(400)
                .courseState(CourseState.HIDDEN)
                .build());

        courseRepository.save(Course.builder()
                .title("Mobile App Development")
                .description("Learn mobile app development")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(5)))
                .language(languages.get(2))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(5))
                .enrollmentCount(40)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Artificial Intelligence")
                .description("Learn artificial intelligence and machine learning")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(6)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(6))
                .enrollmentCount(70)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Cloud Computing")
                .description("Learn cloud computing services and technologies")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(7)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(7))
                .enrollmentCount(90)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Cybersecurity")
                .description("Learn cybersecurity and information security")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(8)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(8))
                .enrollmentCount(60)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("UI/UX Design")
                .description("Learn user interface and user experience design")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(9)))
                .language(languages.get(1))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(9))
                .enrollmentCount(231)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Java advance")
                .description("Learn software development")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(0)))
                .language(languages.get(1))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(11))
                .enrollmentCount(3)
                .courseState(CourseState.PUBLISHED)
                .build());

        courseRepository.save(Course.builder()
                .title("Business Management")
                .description("Learn business management")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(1)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageURL("default")
                .lessons(LessonCommandLineRunner.getLessonsPack(10))
                .enrollmentCount(983)
                .courseState(CourseState.PUBLISHED)
                .build());


    }

}
