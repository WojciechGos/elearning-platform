package project.backend.carts.cart.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.carts.cart.model.Cart;
import project.backend.carts.cart.model.CartStatus;
import project.backend.carts.cartItem.model.CartItem;
import project.backend.carts.cartItem.repository.CartItemRepository;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.repository.CourseRepository;
import project.backend.users.user.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Order(30)
public class CartCommandLineRunner implements CommandLineRunner {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    @Override
    public void run(String... args) throws Exception {

        Optional<Course> course;
        course = courseRepository.findById(1L);
        Course coursePending = courseRepository.findById(8L).get();
        if(course.isPresent()){
            System.out.println("Course found");

            Cart cart = Cart.builder()
                    .cartStatus(CartStatus.COMPLETED)
                    .user(userRepository.findAll().get(0))
                    .build();
            Cart tmp = cartRepository.save(cart);

            CartItem cartItem = CartItem.builder()
                    .course(course.get())
                    .cart(tmp)
                    .build();
            cartItemRepository.save(cartItem);


            tmp.setItems(List.of(cartItem));
            cartRepository.save(tmp);


            /////////////////////////////////////
            Cart cart2 = Cart.builder()
                    .cartStatus(CartStatus.PENDING)
                    .user(userRepository.findAll().get(0))
                    .build();
            Cart tmp2 = cartRepository.save(cart2);

            CartItem cartItem2 = CartItem.builder()
                    .course(coursePending)
                    .cart(tmp2)
                    .build();
            cartItemRepository.save(cartItem2);


            tmp2.setItems(List.of(cartItem2));
            cartRepository.save(tmp2);

        }
        else {
            System.out.println("Course not found");
        }
        System.out.println(course);




    }
}
