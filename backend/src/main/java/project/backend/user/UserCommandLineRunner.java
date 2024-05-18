package project.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(20)
public class UserCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(User.builder()
                .email("jan.kowalski@gmail.com")
                .password(passwordEncoder.encode("password1"))
                .firstName("Jan")
                .lastName("Kowalski")
                .role(Role.USER)
                .build());

        userRepository.save(User.builder()
                .email("michal.wojcik@gmail.com")
                .password(passwordEncoder.encode("adminpassword"))
                .firstName("Michał")
                .lastName("Wójcik")
                .role(Role.ADMIN)
                .build());
    }
}