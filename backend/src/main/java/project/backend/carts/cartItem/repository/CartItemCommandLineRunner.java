package project.backend.carts.cartItem.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;

@RequiredArgsConstructor
@Component
@Order(25)
public class CartItemCommandLineRunner  implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {


    }
}
