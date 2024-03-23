package project.backend.courses.lessonResource;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(1)
public class LessonResourceCommandLineRunner implements CommandLineRunner {

    private final LessonResourceRepository lessonResourceRepository;

    @Override
    public void run(String... args) throws Exception {
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
        lessonResourceRepository.save(new LessonResource());
    }
}
