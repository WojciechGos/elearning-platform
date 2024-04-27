package project.backend.courses.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.courses.course.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
