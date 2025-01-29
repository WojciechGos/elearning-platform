package project.backend.token;

import jakarta.persistence.*;
import lombok.*;
import project.backend.user.User;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String token;

    @Column(unique = true)
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", tokenType=" + tokenType +
                ", revoked=" + revoked +
                ", expired=" + expired +
                ", userId=" + (user != null ? user.getId() : null) +
                '}';
    }
}
