package project.backend.auth.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import project.backend.auth.dto.AuthenticationRequest;
import project.backend.auth.dto.AuthenticationResponse;
import project.backend.auth.dto.RegisterRequest;
import project.backend.auth.service.AuthenticationService;
import project.backend.config.service.JwtService;
import project.backend.user.User;
import project.backend.user.UserMapper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Mock
    private AuthenticationService service;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private HttpServletResponse httpServletResponse;

    @InjectMocks
    private AuthenticationController controller;

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;

    private RegisterRequest registerRequest;
    private AuthenticationRequest authenticationRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password");
        registerRequest.setFirstName("Test");
        registerRequest.setLastName("User");

        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@example.com");
        authenticationRequest.setPassword("password");
    }

    @Test
    void register() {
        when(service.register(any(RegisterRequest.class))).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> response = controller.register(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).register(registerRequest);
    }

    @Test
    void authenticate() {
        when(service.authenticate(any(AuthenticationRequest.class))).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> response = controller.authenticate(authenticationRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).authenticate(authenticationRequest);
    }

    @Test
    void googleLogin() throws IOException {
        controller.googleLogin(httpServletResponse);
        verify(httpServletResponse, times(1)).sendRedirect(anyString());
    }

    @Test
    void googleCallback_NoAuthCode() throws IOException {
        controller.googleCallback("", httpServletResponse);
        verify(httpServletResponse, times(1)).sendRedirect("http://localhost:4200");
    }

    @Test
    void refreshToken_ValidToken() {
        String refreshToken = "validRefreshToken";
        Map<String, String> tokenMap = Collections.singletonMap("refreshToken", refreshToken);
        User user = new User();
        user.setEmail("test@example.com");

        when(jwtService.extractUsername(refreshToken)).thenReturn(user.getEmail());
        when(service.findUserByEmail(user.getEmail())).thenReturn(user);
        when(jwtService.isTokenValid(refreshToken, user)).thenReturn(true);
        when(jwtService.generateAccessToken(user)).thenReturn("newAccessToken");
        when(userMapper.mapToDTO(user)).thenReturn(null);

        ResponseEntity<Object> response = controller.refreshToken(tokenMap);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(jwtService, times(1)).extractUsername(refreshToken);
        verify(service, times(1)).findUserByEmail(user.getEmail());
        verify(jwtService, times(1)).isTokenValid(refreshToken, user);
        verify(jwtService, times(1)).generateAccessToken(user);
    }

    @Test
    void refreshToken_InvalidToken() {
        String refreshToken = "invalidRefreshToken";
        Map<String, String> tokenMap = Collections.singletonMap("refreshToken", refreshToken);

        when(jwtService.extractUsername(refreshToken)).thenReturn("test@example.com");
        when(service.findUserByEmail("test@example.com")).thenReturn(null);

        ResponseEntity<Object> response = controller.refreshToken(tokenMap);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(jwtService, times(1)).extractUsername(refreshToken);
        verify(service, times(1)).findUserByEmail("test@example.com");
    }


    @Test
    void getGoogleClientId() {
        ResponseEntity<Map<String, String>> response = controller.getGoogleClientId();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("googleClientId", googleClientId), response.getBody());
    }
}
