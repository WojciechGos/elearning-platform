package project.backend.courses.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.dto.FilterCourseDTO;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.utils.file.response.FileResponse;

import java.security.Principal;
import java.util.List;

public interface CourseController {

    ResponseEntity<FilterCourseDTO> getCourses(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) List<String> targetAudience,
            @RequestParam(required = false) List<String> languages,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer limit,
            @RequestParam(required = false) List<String> fields
    );

    ResponseEntity<CourseDTO> getCourse(@PathVariable("courseId") Long courseId);

    ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course, Principal principal);

    ResponseEntity<CourseDTO> updateCourse(
            Principal principal,
            @PathVariable("courseId") Long courseId,
            @RequestBody CourseDTO course
    );

    ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId);

    ResponseEntity<List<CourseDTO>> getUsersCourses(
            @RequestParam(required = false) CourseState courseState,
            Principal principal);

    ResponseEntity<FileResponse> getSignedUrlForImageUpload(Long courseId);

    ResponseEntity<Void> deleteCourseImage(Long courseId);
    ResponseEntity<CourseDTO> completeCourse(@PathVariable Long courseId, Principal principal);
}
