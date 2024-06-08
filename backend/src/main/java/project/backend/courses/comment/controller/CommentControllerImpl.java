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

    @Override
    @PostMapping
    public ResponseEntity<CommentDTO> addComment(Principal principal, @RequestBody CommentRequest commentRequest) {
        CommentDTO savedComment = commentService.addComment(principal, commentRequest);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByCourseId(@PathVariable Long courseId) {
        List<CommentDTO> comments = commentService.getCommentsByCourseId(courseId);
        return ResponseEntity.ok(comments);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
