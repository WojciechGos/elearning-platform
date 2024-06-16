package project.backend.courses.course.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.course.dto.CourseDTO;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.dto.FilterCourseDTO;
import project.backend.courses.course.model.CourseState;
import project.backend.courses.course.service.CourseService;
import project.backend.courses.utils.file.response.FileResponse;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseControllerImpl implements CourseController {
    private final CourseService courseService;

    @Override
    @GetMapping
    public ResponseEntity<FilterCourseDTO> getCourses(
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
    ) {
        FilterCourseDTO courses = courseService.getCourses(
                keyword,
                categories,
                minPrice,
                maxPrice,
                minRating,
                targetAudience,
                languages,
                page,
                limit,
                fields
        );
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable("courseId") Long courseId) {
        CourseDTO course = courseService.getCourseDTOById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course, Principal principal) {
        CourseDTO newCourse = courseService.createCourse(course, principal);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(
            Principal principal,
            @PathVariable("courseId") Long courseId,
            @RequestBody CourseDTO course
    ) {

        CourseDTO updatedCourse = courseService.updateCourse(courseId, course, principal);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<List<CourseDTO>> getUsersCourses(@RequestParam(required = false) CourseState courseState, Principal principal) {
        return new ResponseEntity<>(courseService.getUsersCourse(courseState, principal), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{courseId}/image")
    public ResponseEntity<FileResponse> getSignedUrlForImageUpload(@PathVariable("courseId") Long courseId) {
        FileResponse response = new FileResponse(courseService.getSignedUrlForImageUpload(courseId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{courseId}/image")
    public ResponseEntity<Void> deleteCourseImage(@PathVariable("courseId") Long courseId) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{courseId}/complete")
    public ResponseEntity<CourseDTO> completeCourse(@PathVariable Long courseId, Principal principal) {
        return new ResponseEntity<>(courseService.completeCourse(courseId, principal), HttpStatus.OK);
    }

    @GetMapping("/completed-courses")
    public ResponseEntity<List<CourseDTO>> getUsersCompletedCourses(Principal principal) {
        List<CourseDTO> completedCourses = courseService.getUsersCourse(CourseState.COMPLETED, principal);
        return new ResponseEntity<>(completedCourses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}/certificate")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long courseId, Principal principal) {
        byte[] certificate = courseService.generateCertificate(courseId, principal);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificate.pdf");
        return new ResponseEntity<>(certificate, headers, HttpStatus.OK);
    }

}
