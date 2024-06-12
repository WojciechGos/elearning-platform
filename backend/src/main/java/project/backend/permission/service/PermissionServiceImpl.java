package project.backend.permission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.carts.cart.service.CartService;
import project.backend.courses.course.model.Course;
import project.backend.user.User;
import project.backend.user.UserService;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final UserService userService;

    @Override
    public boolean isAuthor(Course course, Principal principal) {
        return course.getAuthor().getEmail().equals(principal.getName());
    }

    @Override
    public boolean hasRole(Principal principal, String role) {
        User user = userService.getUserByEmail(principal.getName());
        return user.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(role));
    }



    @Override
    public boolean hasPermissionToEditCourse(Course course, Principal principal) {

        if(isAuthor(course, principal)){
            return true;
        }
        return hasRole(principal, "ROLE_ADMIN");
    }
}
