package com.jinny7.todoapp.controller;

import com.jinny7.todoapp.dto.LoginRequestDTO;
import com.jinny7.todoapp.dto.UserRequestDTO;
import com.jinny7.todoapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1.0/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        String message = userService.registerUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = userService.loginUser(loginRequestDTO);
        return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("로그인에 성공했습니다.");
    }
}
