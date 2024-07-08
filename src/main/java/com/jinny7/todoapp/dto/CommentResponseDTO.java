package com.jinny7.todoapp.dto;

import com.jinny7.todoapp.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDTO {
    private Long commentId;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userName = comment.getUserName();
        this.createdAt = comment.getCreatedAt();
    }
}
