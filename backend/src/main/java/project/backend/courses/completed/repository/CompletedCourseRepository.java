package project.backend.courses.completed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.courses.completed.model.CompletedCourse;

import java.util.List;

@Repository
public interface CompletedCourseRepository extends JpaRepository<CompletedCourse, Long> {
    List<CompletedCourse> findByUserId(Long userId);
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
