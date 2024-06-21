package project.backend.courses.course.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.backend.courses.course.model.Course;
import project.backend.user.User;
import project.backend.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CourseRepositoryTest {

    @Mock
    private CourseRepository underTest;

    @Autowired
    private UserRepository userRepository;

    private final String email = "test@test.com";

    @BeforeEach
    void setUp() {
        // given
        User user = User.builder().email(email).build();
        Course course = Course.builder().author(user).build();
        userRepository.save(user);
        underTest.save(course);
    }

    @AfterEach
    void tearDown() throws Exception{
        underTest.deleteAll();
        userRepository.deleteAll();
    }

//    @Test
//    public void Should_NotFindCoursesByAuthorEmail_When_AuthorWithGivenEmailDoestExist() {
//
//        // when
//        List<Course> courses = underTest.findCoursesByAuthorEmail("not@exist.com");
//
//        // then
//        assertThat(courses).isEmpty();
//    }
}