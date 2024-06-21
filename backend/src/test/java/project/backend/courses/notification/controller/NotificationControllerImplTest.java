package project.backend.courses.notification.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.backend.auth.dto.AuthenticationResponse;
import project.backend.auth.dto.RegisterRequest;
import project.backend.auth.service.AuthenticationService;
import project.backend.courses.notification.model.Notification;
import project.backend.courses.notification.model.NotificationStatus;
import project.backend.courses.notification.repository.NotificationRepository;
import project.backend.user.User;
import project.backend.user.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotificationControllerImplTest {
    private MockMvc mockMvc;

    @Autowired
    public NotificationControllerImplTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private String token;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationRepository notificationRepository;

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

        User user = userService.getUserByEmail(email);

        Notification notification1 = Notification.builder()
                .message("message1")
                .notificationStatus(NotificationStatus.UNREAD)
                .build();
        notification1 = notificationRepository.save(notification1);
        user.setNotificationList(List.of(notification1));
        userService.saveUser(user);

    }

    @Test
    public void Should_ReturnUserNotifications_When_SendGetRequest() throws Exception {
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/notifications/me")
                        .header("Authorization", "Bearer " + token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1))); // assuming there is 1 notification for this user
    }

    @Test
    public void Should_ReturnUserNotificationsByStatus_When_SendGetRequest() throws Exception {
        // Given
        NotificationStatus notificationStatus = NotificationStatus.UNREAD; // or any other status you want to test

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/notifications/" + notificationStatus + "/me")
                        .header("Authorization", "Bearer " + token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1))) // assuming there is 1 notification for this user with the given status
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].notificationStatus", Matchers.is(notificationStatus.name())));
    }

    @Test
    public void Should_UpdateNotificationStatus_When_SendPutRequest() throws Exception {
        // Given
        Long notificationId = 1L; // assuming there is a notification with id 1
        NotificationStatus notificationStatus = NotificationStatus.READ; // or any other status you want to test

        // Create a JSON object to represent the Notification
        String notificationJson = "{ \"notificationStatus\": \"" + notificationStatus.name() + "\" }";

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/notifications/" + notificationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(notificationJson)
                        .header("Authorization", "Bearer " + token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.notificationStatus", Matchers.is(notificationStatus.name())));
    }
}