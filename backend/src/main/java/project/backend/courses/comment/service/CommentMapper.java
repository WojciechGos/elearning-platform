package project.backend.courses.comment.service;

import org.springframework.stereotype.Component;
import project.backend.courses.comment.dto.CommentDTO;
import project.backend.courses.comment.model.Comment;

@Component
public class CommentMapper {
    public CommentDTO mapToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCourse().getId(),
                comment.getAuthor().getId()
        );
    }
}
