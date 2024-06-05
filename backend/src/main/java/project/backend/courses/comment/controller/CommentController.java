package project.backend.courses.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import project.backend.courses.comment.dto.CommentDTO;
import project.backend.courses.comment.request.CommentRequest;

import java.security.Principal;
import java.util.List;

public interface CommentController {
    ResponseEntity<CommentDTO> addComment(Principal principal, @RequestBody CommentRequest commentRequest);
    ResponseEntity<List<CommentDTO>> getAllCommentsForCourse(@PathVariable Long courseId);
    ResponseEntity<Void> deleteComment(@PathVariable Long id);
}
