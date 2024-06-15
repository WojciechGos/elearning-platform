package project.backend.courses.comment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.courses.comment.model.Comment;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.users.user.User;
import project.backend.users.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@Component
@Order(30)
public class CommentCommandLineRunner implements CommandLineRunner {
    private final CommentRepository commentRepository;
    private final CourseService courseService;
    private final UserService userService;
    @Override
    public void run(String... args) throws Exception {
        Course course = courseService.getCourseById(1L);
        List<User> users = userService.getAllUsers();

        List<Comment> comments = List.of(
                Comment.builder().content("This is a great course!").course(course).author(users.get(0)).build(),
                Comment.builder().content("I really enjoyed this course!").course(course).author(users.get(3)).build()
        );

        commentRepository.saveAll(comments);
    }
}
