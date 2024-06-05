package project.backend.courses.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.courses.comment.dto.CommentDTO;
import project.backend.courses.comment.request.CommentRequest;
import project.backend.courses.comment.service.CommentService;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/comments")
public class CommentControllerImpl implements CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(Principal principal, @RequestBody CommentRequest commentRequest) {
        CommentDTO savedComment = commentService.addComment(principal, commentRequest);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllCommentsForCourse(@PathVariable Long courseId) {
        List<CommentDTO> comments = commentService.getAllCommentsForCourse(courseId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
