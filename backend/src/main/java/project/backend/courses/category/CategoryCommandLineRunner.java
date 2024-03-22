package project.backend.courses.category;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
@Order(10)
public class CategoryCommandLineRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

    }
}
