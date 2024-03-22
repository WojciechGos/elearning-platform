package project.backend.courses.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(10)
public class LessonCommandLineRunner implements CommandLineRunner {

    private final LessonRepository lessonRepository;

    @Override
    public void run(String... args) throws Exception {
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
        lessonRepository.save(new Lesson());
    }
}
