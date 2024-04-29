package project.backend.courses.lessonResource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.courses.lessonResource.model.LessonResource;

@Repository
public interface LessonResourceRepository extends JpaRepository<LessonResource, Long> {
}
