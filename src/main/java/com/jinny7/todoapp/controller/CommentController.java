package com.jinny7.todoapp.controller;

import com.jinny7.todoapp.dto.CommentRequestDTO;
import com.jinny7.todoapp.dto.CommentResponseDTO;
import com.jinny7.todoapp.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> postComment(@RequestBody CommentRequestDTO dto, @AuthenticationPrincipal User userDetails) {
        var comment = commentService.createComment(dto, userDetails.getUsername());
        var response = new CommentResponseDTO(comment);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByTodoId(@PathVariable Long todoId) {
        var comments = commentService.getCommentsByTodoId(todoId);
        return ResponseEntity.ok().body(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable Long commentId,
            @RequestParam String content,
            @AuthenticationPrincipal User userDetails) {
        var updatedComment = commentService.updateComment(commentId, content, userDetails.getUsername());
        var response = new CommentResponseDTO(updatedComment);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal User userDetails) {
        commentService.deleteComment(commentId, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
