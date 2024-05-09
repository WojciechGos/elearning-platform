package project.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(20)
public class UserCommandLineRunner implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        /*userRepository.save(User.builder()
                .email("anna@wp.pl")
                .password("password1")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("michal@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("kasia@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("jan@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("marek@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("adam@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("tomasz@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("karol@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("wiktor@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("magda@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("patrycja@wp.pl")
                .role(Role.USER)
                .build()
        );

        userRepository.save(User.builder()
                .email("zuzanna@wp.pl")
                .role(Role.USER)
                .build()
        );*/
    }
}
