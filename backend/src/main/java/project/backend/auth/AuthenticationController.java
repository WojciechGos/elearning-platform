package project.backend.auth;

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
import project.backend.user.User;
import project.backend.config.JwtService;
import project.backend.user.UserDTO;
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
            
            String redirectUrl = String.format("http://localhost:4200?authCode=%s", authCode);
            response.sendRedirect(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error during Google token verification: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @PostMapping("/exchange-code")
    public ResponseEntity<?> exchangeCode(@RequestBody Map<String, String> authCodeMap) {
        String authCode = authCodeMap.get("code");
        if (authCode == null || authCode.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Auth code is missing");
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

                return ResponseEntity.ok(new AuthenticationResponse(jwtAccessToken, refreshToken, userMapper.mapToDTO(user)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google ID token");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during Google token verification: " + e.getMessage());
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
