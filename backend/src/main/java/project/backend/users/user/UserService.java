package project.backend.users.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.backend.exception.types.ResourceNotFoundException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository repository;
  private final UserMapper userMapper;
  public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

    var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

    // check if the current password is correct
    if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
      throw new IllegalStateException("Wrong password");
    }
    // check if the two new passwords are the same
    if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
      throw new IllegalStateException("Password are not the same");
    }

    // update the password
    user.setPassword(passwordEncoder.encode(request.getNewPassword()));

    // save the new password
    repository.save(user);
  }

  public List<UserDTO> getAllUsersDTO() {
    List<User> users = repository.findAll();
    return users.stream()
            .map(userMapper::mapToDTO)
            .collect(Collectors.toList());
  }

  public List<User> getAllUsers() {
    return repository.findAll();
  }

  public User getUserByEmail(String email) {
    return repository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException(
                    String.format("User with email [%s] not found.", email)
            ));
  }
}
