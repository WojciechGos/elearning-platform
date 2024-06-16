package project.backend.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.http.HttpStatus;
import project.backend.auth.dto.AuthenticationRequest;
import project.backend.auth.dto.AuthenticationResponse;
import project.backend.auth.service.AuthenticationService;
import project.backend.auth.dto.RegisterRequest;
import project.backend.user.User;
import project.backend.config.service.JwtService;
import project.backend.user.UserMapper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        ResponseEntity<Object> response = service.register(request);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        ResponseEntity<Object> response = service.authenticate(request);
        return response;
    }

    @GetMapping("/google-login")
    public void googleLogin(HttpServletResponse response) throws IOException {
        String redirectUri = "http://localhost:8080/api/v1/auth/google/callback";
        String clientId = googleClientId;
        String scope = "openid profile email";
        String responseType = "code";

        String url = String.format(
                "https://accounts.google.com/o/oauth2/v2/auth?client_id=%s&redirect_uri=%s&scope=%s&response_type=%s",
                clientId, redirectUri, scope, responseType);

        response.sendRedirect(url);
    }

    @GetMapping("/google/callback")
    public void googleCallback(@RequestParam("code") String code, HttpServletResponse response) {

        try {
            String authCode = code;
            if (authCode == null || authCode.isEmpty()) {
                String redirectUrl = String.format("http://localhost:4200");
                response.sendRedirect(redirectUrl);

            }

            try {
                NetHttpTransport transport = new NetHttpTransport();
                JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

                GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                        transport,
                        jsonFactory,
                        "https://oauth2.googleapis.com/token",
                        googleClientId,
                        googleClientSecret,
                        authCode,
                        "http://localhost:8080/api/v1/auth/google/callback")
                        .execute();

                String idTokenString = tokenResponse.getIdToken();

                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                        .setAudience(Collections.singletonList(googleClientId))
                        .build();

                GoogleIdToken idToken = verifier.verify(idTokenString);
                if (idToken != null) {
                    GoogleIdToken.Payload payload = idToken.getPayload();

                    User user = service.findUserByEmail(payload.getEmail());
                    if (user == null) {
                        user = service.createUserFromGooglePayload(payload);
                    }

                    String jwtAccessToken = jwtService.generateAccessToken(user);
                    String refreshToken = jwtService.generateRefreshToken(user);

                    String strigifiedUser = String.format("{\"id\":%d,\"email\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\"}",
                            user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
                    String redirectUrl = "http://localhost:4200/login?jwtAccessToken=" + jwtAccessToken + "&refreshToken="+ refreshToken +"&user=" +strigifiedUser;

                    System.out.println(redirectUrl);
                    response.sendRedirect(redirectUrl);
                } else {
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google ID token");
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }catch (Exception e) {
            e.printStackTrace();

        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(@RequestBody Map<String, String> tokenMap) {
        String refreshToken = tokenMap.get("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is missing");
        }

        try {
            String userEmail = jwtService.extractUsername(refreshToken);
            User user = service.findUserByEmail(userEmail);
            if (user == null || !jwtService.isTokenValid(refreshToken, user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }

            String newAccessToken = jwtService.generateAccessToken(user);
            return ResponseEntity.ok(new AuthenticationResponse(newAccessToken, refreshToken, userMapper.mapToDTO(user)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during token refresh: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        System.out.println("LOGOUT");
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/google-client-id")
    public ResponseEntity<Map<String, String>> getGoogleClientId() {
        Map<String, String> response = Collections.singletonMap("googleClientId", googleClientId);
        return ResponseEntity.ok(response);
    }

}
