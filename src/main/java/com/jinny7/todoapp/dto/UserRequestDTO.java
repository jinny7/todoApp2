package com.jinny7.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String username;
    private String password;
    private String nickname;
}
