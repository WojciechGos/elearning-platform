package project.backend.courses.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.courses.comment.dto.CommentDTO;
import project.backend.courses.comment.model.Comment;
import project.backend.courses.comment.repository.CommentRepository;
import project.backend.courses.comment.request.CommentRequest;
import project.backend.courses.course.model.Course;
import project.backend.courses.course.service.CourseService;
import project.backend.user.User;
import project.backend.user.UserService;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final CommentMapper commentMapper;

    public CommentDTO addComment(Principal principal, CommentRequest commentRequest) {
        User author = userService.getUserByEmail(principal.getName());
        Course course = courseService.getCourseById(commentRequest.courseId());

        Comment comment = Comment.builder()
                .content(commentRequest.content())
                .course(course)
                .author(author)
                .build();

        return commentMapper.mapToDTO(commentRepository.save(comment));
    }

    public List<CommentDTO> getAllCommentsForCourse(Long courseId) {
        return commentRepository.findByCourseId(courseId);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
