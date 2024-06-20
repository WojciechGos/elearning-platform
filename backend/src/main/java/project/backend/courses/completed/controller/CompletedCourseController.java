package project.backend.courses.completed.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.completed.service.CompletedCourseService;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.mapper.CourseDTOMapper;
import project.backend.courses.course.model.Course;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/completed-courses")
@RequiredArgsConstructor
public class CompletedCourseController {
    private final CompletedCourseService completedCourseService;
    private final CourseDTOMapper courseDTOMapper;

    @PutMapping("/{courseId}/complete")
    public ResponseEntity<Void> completeCourse(@PathVariable Long courseId, Principal principal) {
        completedCourseService.completeCourse(courseId, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getCompletedCourses(Principal principal) {
        List<CourseDTO> completedCourses = completedCourseService.getCompletedCourses(principal).stream()
                .map(completedCourse -> courseDTOMapper.toDTO(completedCourse.getCourse()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(completedCourses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}/certificate")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long courseId, Principal principal) {
        byte[] certificate = completedCourseService.generateCertificate(courseId, principal);
        Course course = completedCourseService.getCourseById(courseId);
        String fileName = course.getTitle().replaceAll(" ", "_") + "-certificate.pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(certificate, headers, HttpStatus.OK);
    }

}
