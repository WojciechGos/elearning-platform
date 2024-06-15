package project.backend.courses.course.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.courses.category.model.Category;
import project.backend.courses.category.service.CategoryService;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.language.model.Language;
import project.backend.courses.language.service.LanguageService;
import project.backend.courses.lesson.model.Lesson;
import project.backend.courses.lesson.repository.LessonCommandLineRunner;
import project.backend.courses.lesson.repository.LessonRepository;
import project.backend.users.user.User;
import project.backend.users.user.UserService;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Component
@Order(20)
public class CourseCommandLineRunner implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final LanguageService languageService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final LessonRepository lessonRepository;

    @Value("${aws.s3.url}")
    private String awsS3Url;

    @Override
    public void run(String... args) throws Exception {

        List<Language> languages = languageService.getLanguages();
        List<Category> categories = categoryService.getCategories();
        List<User> users = userService.getAllUsers();
        Course course;

        course = courseRepository.save(Course.builder()
                .title("Java Programming")
                .description("Learn Java programming language")
                .price(new BigDecimal(600))
                .categories(List.of(categories.get(0)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(1001)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(1))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(0));

        course = courseRepository.save(Course.builder()
                .title("Business basic concepts")
                .description("Learn business basic concepts")
                .price(new BigDecimal(400))
                .discountPrice(new BigDecimal("219.99"))
                .categories(List.of(categories.get(1)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(200)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(1));

        course = courseRepository.save(Course.builder()
                .title("Python Programming")
                .description("Learn Python programming language")
                .price(new BigDecimal(530))
                .categories(List.of(categories.get(2)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(200)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(0))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(2));

        course = courseRepository.save(Course.builder()
                .title("Data Science")
                .description("Learn data science and analytics")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(3)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(300)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(3));

        course = courseRepository.save(Course.builder()
                .title("Web Development")
                .description("Learn web development technologies")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(4)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(400)
                .courseState(CourseState.HIDDEN)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(4));

        course = courseRepository.save(Course.builder()
                .title("Mobile App Development")
                .description("Learn mobile app development")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(5)))
                .language(languages.get(2))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(40)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(5));

        course = courseRepository.save(Course.builder()
                .title("Artificial Intelligence")
                .description("Learn artificial intelligence and machine learning")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(6)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(70)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(6));

        course = courseRepository.save(Course.builder()
                .title("Cloud Computing")
                .description("Learn cloud computing services and technologies")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(7)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(90)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(7));

        course = courseRepository.save(Course.builder()
                .title("Cybersecurity")
                .description("Learn cybersecurity and information security")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(8)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(60)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(8));

        course = courseRepository.save(Course.builder()
                .title("UI/UX Design")
                .description("Learn user interface and user experience design")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(9)))
                .language(languages.get(1))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(231)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(9));

        course = courseRepository.save(Course.builder()
                .title("Java advance")
                .description("Learn software development")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(0)))
                .language(languages.get(1))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(3)
                .courseState(CourseState.CREATING)
                .author(users.get(0))
                .build());

        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(10));

        course = courseRepository.save(Course.builder()
                .title("Business Management")
                .description("Learn business management")
                .price(new BigDecimal(100))
                .categories(List.of(categories.get(1)))
                .language(languages.get(0))
                .totalDuration(Duration.ZERO)
                .rating(4.5)
                .imageUrl(awsS3Url + "/public/courses/0/production-image.png")
                .enrollmentCount(983)
                .courseState(CourseState.PUBLISHED)
                .author(users.get(3))
                .build());
        attachCourseToLesson(course, LessonCommandLineRunner.getLessonsPack(11));

    }

    public void attachCourseToLesson(Course course, List<Lesson> lessons) {
        lessons.forEach(lesson -> {
            lesson.setCourse(course);
            lessonRepository.save(lesson);
        });
    }
}
