package project.backend.courses.language.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.courses.language.model.Language;

@RequiredArgsConstructor
@Component
@Order(10)
public class LanguageCommandLineRunner implements CommandLineRunner {

    private final LanguageRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Language("English"));
        repository.save(new Language("Spanish"));
        repository.save(new Language("Polish"));
    }
}
