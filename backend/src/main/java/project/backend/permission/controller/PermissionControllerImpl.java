package project.backend.permission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.permission.service.PermissionService;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/permissions")
public class PermissionControllerImpl implements PermissionController {

    private final PermissionService permissionService;

    @Override
    @GetMapping("/roles/{role}/me")
    public ResponseEntity<Boolean> hasRole(@PathVariable("role") String role, Principal principal) {
        return ResponseEntity.ok(permissionService.hasRole(principal, role));
    }

}
