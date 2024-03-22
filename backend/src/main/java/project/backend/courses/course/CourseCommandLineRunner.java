package project.backend.courses.course;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(20)
public class CourseCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CourseCommandLineRunner.class);

    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        courseRepository.save(new Course());
        courseRepository.save(new Course());
        courseRepository.save(new Course());
        courseRepository.save(new Course());
        courseRepository.save(new Course());
    }
}
