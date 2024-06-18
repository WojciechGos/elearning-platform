package project.backend.courses.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.backend.auth.AuthenticationResponse;
import project.backend.auth.AuthenticationService;
import project.backend.auth.RegisterRequest;
import project.backend.config.JwtService;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.dto.FilterCourseDTO;
import project.backend.user.User;
import project.backend.user.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseControllerImplTestIT {

    private MockMvc mockMvc;

    @Autowired
    public CourseControllerImplTestIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private String token;

    @Autowired
    private AuthenticationService authenticationService;

    private final String email = "test@test.com";

    @BeforeAll
    void setup() {
        AuthenticationResponse response = authenticationService.registerRefactor(RegisterRequest.builder()
                .email(email)
                .password("password")
                .firstName("test")
                .lastName("test")
                .build());
        token = response.getAccessToken();
    }

    @Test
    public void Should_ReturnCourses_When_SendGetRequest() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/courses"
                        ).header("Authorization", "Bearer " + token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void Should_CreateCourse_When_SendPostRequest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/courses")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(
                                CourseDTO.builder()
                                        .build()
                        )))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


}