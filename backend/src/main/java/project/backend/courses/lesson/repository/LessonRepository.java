package project.backend.courses.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.courses.lesson.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
