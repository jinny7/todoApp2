package com.jinny7.todoapp.controller;

import com.jinny7.todoapp.repository.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDTO {
    private String title;
    private String content;
    private String userName;
    private String password; // 비밀번호 필드 추가

    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .content(content)
                .userName(userName)
                .password(password) // 비밀번호 설정
                .build();
    }
}
