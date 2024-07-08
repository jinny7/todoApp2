package com.jinny7.todoapp.dto;

import com.jinny7.todoapp.entity.Comment;
import com.jinny7.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private String content;
    private String userName;
    private Long todoId;

    public Comment toEntity(Todo todo) {
        return Comment.builder()
                .content(content)
                .todo(todo)
                .userName(userName)
                .build();
    }
}
