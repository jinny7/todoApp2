package com.jinny7.todoapp.dto;

import com.jinny7.todoapp.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDTO {
    private Long todoID;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public TodoResponseDTO(Todo todo) {
        this.todoID = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createdAt = todo.getCreatedAt();
    }
}
