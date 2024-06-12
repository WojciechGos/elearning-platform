package project.backend.permission.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

public interface PermissionController {
    ResponseEntity<Boolean> hasRole(@RequestBody String role, Principal principal);
}
