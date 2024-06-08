package project.backend.courses.comment.service;

import project.backend.courses.comment.dto.CommentDTO;
import project.backend.courses.comment.request.CommentRequest;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    CommentDTO addComment(Principal principal, CommentRequest commentRequest);
    List<CommentDTO> getCommentsByCourseId(Long courseId);
    void deleteComment(Long commentId);
}
