package com.jinny7.todoapp.entity;

import com.jinny7.todoapp.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public User(UserRequestDTO userRequestDTO) {
        this.username = userRequestDTO.getUsername();
        this.password = userRequestDTO.getPassword();
        this.nickname = userRequestDTO.getNickname();
    }
}
