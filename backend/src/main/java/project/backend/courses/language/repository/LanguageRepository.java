package project.backend.courses.language.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.courses.language.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
