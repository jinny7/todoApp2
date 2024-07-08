package com.jinny7.todoapp.service;

import com.jinny7.todoapp.dto.LoginRequestDTO;
import com.jinny7.todoapp.dto.UserRequestDTO;
import com.jinny7.todoapp.entity.User;
import com.jinny7.todoapp.repository.UserRepository;
import com.jinny7.todoapp.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 ID입니다.");
        }

        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User user = new User(userRequestDTO);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    public String loginUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다.");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
