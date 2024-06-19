package project.backend.courses.completed.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.courses.completed.model.CompletedCourse;
import project.backend.courses.completed.repository.CompletedCourseRepository;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.repository.CourseRepository;
import project.backend.exception.types.ForbiddenException;
import project.backend.exception.types.ResourceNotFoundException;
import project.backend.user.User;
import project.backend.user.UserService;

import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletedCourseService {
    private final CompletedCourseRepository completedCourseRepository;
    private final UserService userService;
    private final CourseRepository courseRepository;

    @Transactional
    public void completeCourse(Long courseId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

        if (completedCourseRepository.existsByUserIdAndCourseId(user.getId(), courseId)) {
            throw new ForbiddenException("Course already completed.");
        }

        CompletedCourse completedCourse = CompletedCourse.builder()
                .user(user)
                .course(course)
                .build();

        completedCourseRepository.save(completedCourse);
    }

    public List<CompletedCourse> getCompletedCourses(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        return completedCourseRepository.findByUserId(user.getId());
    }

    public byte[] generateCertificate(Long courseId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

        boolean hasCompleted = completedCourseRepository.existsByUserIdAndCourseId(user.getId(), courseId);
        if (!hasCompleted) {
            throw new ForbiddenException("You have not completed this course.");
        }

        return createPdfCertificate(user, course);
    }

    private byte[] createPdfCertificate(User user, Course course) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);
            Font subTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Font bodyFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);
            Paragraph title = new Paragraph("Certificate of Completion", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(50);
            document.add(title);

            Paragraph subTitle = new Paragraph("This is to certify that", subTitleFont);
            subTitle.setAlignment(Element.ALIGN_CENTER);
            subTitle.setSpacingAfter(30);
            document.add(subTitle);

            Paragraph name = new Paragraph(user.getFirstName() + " " + user.getLastName(), subTitleFont);
            name.setAlignment(Element.ALIGN_CENTER);
            name.setSpacingAfter(30);
            document.add(name);

            Paragraph courseTitle = new Paragraph("has successfully completed the course", bodyFont);
            courseTitle.setAlignment(Element.ALIGN_CENTER);
            courseTitle.setSpacingAfter(20);
            document.add(courseTitle);

            Paragraph courseName = new Paragraph(course.getTitle(), bodyFont);
            courseName.setAlignment(Element.ALIGN_CENTER);
            courseName.setSpacingAfter(50);
            document.add(courseName);

            LineSeparator separator = new LineSeparator();
            separator.setLineColor(BaseColor.BLACK);
            separator.setPercentage(80);
            separator.setAlignment(Element.ALIGN_CENTER);
            document.add(separator);

            Paragraph footer = new Paragraph("Congratulations!", subTitleFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(50);
            document.add(footer);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error generating PDF", e);
        }

        return out.toByteArray();
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
    }

}
