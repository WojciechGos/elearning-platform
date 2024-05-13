package project.backend.user;

public record UserDTO(
    Long id,
    String email,
    String firstName,
    String lastName
) {
}
