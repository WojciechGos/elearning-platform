package project.backend.users.permission.service;

import project.backend.courses.course.model.Course;

import java.security.Principal;

public interface PermissionService {
    boolean isAuthor(Course course, Principal principal);

    boolean hasRole(Principal principal, String role);

    boolean hasPermissionToEditCourse(Course course, Principal principal);
}
