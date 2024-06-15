package project.backend.courses.comment.service;

import org.springframework.stereotype.Component;
import project.backend.courses.comment.dto.CommentDTO;
import project.backend.courses.comment.model.Comment;
import project.backend.courses.course.model.Course;
import project.backend.users.user.User;

@Component
public class CommentMapper {
    public CommentDTO mapToDTO(Comment comment) {
        User author = comment.getAuthor();
        Course course = comment.getCourse();
        boolean isCourseAuthor = course.getAuthor().getId().equals(author.getId());

        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                author.getFirstName(),
                author.getLastName(),
                comment.getCreatedOn(),
                isCourseAuthor
        );
    }
}
