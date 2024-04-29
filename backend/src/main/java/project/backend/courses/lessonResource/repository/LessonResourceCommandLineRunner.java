package project.backend.courses.lessonResource.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.courses.lessonResource.model.LessonResource;

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
