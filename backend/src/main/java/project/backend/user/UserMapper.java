package project.backend.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
