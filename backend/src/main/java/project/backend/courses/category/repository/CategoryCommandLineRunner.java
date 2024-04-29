package project.backend.courses.category.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import project.backend.courses.category.model.Category;

@Configuration
@RequiredArgsConstructor
@Order(10)
public class CategoryCommandLineRunner implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {
        categoryRepository.save(new Category("Java","Courses related to Java programming language"));
        categoryRepository.save(new Category("Business","Courses related to business management"));
        categoryRepository.save(new Category("Python","Courses related to Python programming language"));
        categoryRepository.save(new Category("Data Science","Courses related to data science and analytics"));
        categoryRepository.save(new Category("Web Development","Courses related to web development technologies"));
        categoryRepository.save(new Category("Mobile App Development","Courses related to mobile app development"));
        categoryRepository.save(new Category("Artificial Intelligence","Courses related to artificial intelligence and machine learning"));
        categoryRepository.save(new Category("Cloud Computing","Courses related to cloud computing services and technologies"));
        categoryRepository.save(new Category("Cybersecurity","Courses related to cybersecurity and information security"));
        categoryRepository.save(new Category("UI/UX Design","Courses related to user interface and user experience design"));
    }
}
