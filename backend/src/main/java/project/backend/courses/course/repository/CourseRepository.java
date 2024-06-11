package project.backend.courses.course.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.model.CourseState;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    List<Course> findCoursesByAuthorEmail(String email);
    List<Course> findCoursesByAuthorEmailAndCourseState(String email, CourseState courseState);

    @EntityGraph(attributePaths = {"lessons"})
    Optional<Course> findCourseWithSortedLessonsByIdOrderByLessonsLessonNumber(Long courseId);
}
