package project.backend.permission.service;

import project.backend.courses.course.model.Course;

import java.security.Principal;

public interface PermissionService {
    boolean isAuthor(Course course, Principal principal);

    boolean hasRole(Principal principal, String role);

    boolean canWatchCourse(Course course, Principal principal);

    boolean hasPermissionToEditCourse(Course course, Principal principal);
}
