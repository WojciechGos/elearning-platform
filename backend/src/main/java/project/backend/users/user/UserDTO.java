package project.backend.users.user;

public record UserDTO(
    Long id,
    String email,
    String firstName,
    String lastName
) {
}
