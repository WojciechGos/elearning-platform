package project.backend.courses.lessonResource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonResourceRepository extends JpaRepository<LessonResource, Long> {
}
