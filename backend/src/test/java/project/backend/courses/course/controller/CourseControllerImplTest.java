package project.backend.courses.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import project.backend.courses.course.dto.FilterCourseDTO;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.repository.CourseRepository;
import project.backend.courses.course.service.CourseService;
import project.backend.user.User;
import project.backend.user.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CourseControllerImpl.class)
@ExtendWith(MockitoExtension.class)
class CourseControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private CourseRepository underTest;

    @Autowired
    private UserRepository userRepository;

    private FilterCourseDTO filterCourseDTO;

    private final String email = "test@test.com";

    @BeforeEach
    void setUp() {
        User user = User.builder().email(email).build();
        Course course = Course.builder().author(user).build();
        userRepository.save(user);
        underTest.save(course);
    }

    @AfterEach
    void tearDown() {
    }

    /*@Test
    public void Should_ReturnCourses_When_SendGetRequest() throws Exception {
        // given
        given(courseService.getCourses(null, null, null, null, null, null, null, 0, 5, null)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(get("/api/v1/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filterCourseDTO)));
        // then

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }*/


}