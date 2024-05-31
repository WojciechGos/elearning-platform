package project.backend.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("SELECT t FROM Token t WHERE t.user.id = :userId AND (t.expired = false OR t.revoked = false)")
    List<Token> findAllValidTokenByUser(Integer userId);

    Optional<Token> findByToken(String token);

    @Modifying
    @Query("UPDATE Token t SET t.expired = true, t.revoked = true WHERE t.user.email = :email")
    void revokeAllTokensByEmail(String email);
}
