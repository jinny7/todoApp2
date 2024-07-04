package com.jinny7.todoapp.controller;

import com.jinny7.todoapp.CommonResponseDTO;
import com.jinny7.todoapp.repository.Todo;
import com.jinny7.todoapp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1.0/todo")
@Controller
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<CommonResponseDTO<TodoResponseDTO>> postTodo(@RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.createTodo(dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        CommonResponseDTO<TodoResponseDTO> commonResponse = CommonResponseDTO.<TodoResponseDTO>builder()
                .statusCode(200)
                .msg("생성 완료")
                .data(response)
                .build();
        return ResponseEntity.ok().body(commonResponse);
    }

    @GetMapping("{todoId}")
    public ResponseEntity<CommonResponseDTO<TodoResponseDTO>> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        CommonResponseDTO<TodoResponseDTO> commonResponse = CommonResponseDTO.<TodoResponseDTO>builder()
                .statusCode(200)
                .msg("Todo retrieved successfully")
                .data(response)
                .build();
        return ResponseEntity.ok().body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<CommonResponseDTO<List<TodoResponseDTO>>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDTO> response = todos.stream().map(TodoResponseDTO::new).collect(Collectors.toList());
        CommonResponseDTO<List<TodoResponseDTO>> commonResponse = CommonResponseDTO.<List<TodoResponseDTO>>builder()
                .statusCode(200)
                .msg("Todos retrieved successfully")
                .data(response)
                .build();
        return ResponseEntity.ok().body(commonResponse);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponseDTO<TodoResponseDTO>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        CommonResponseDTO<TodoResponseDTO> commonResponse = CommonResponseDTO.<TodoResponseDTO>builder()
                .statusCode(200)
                .msg("Todo updated successfully")
                .data(response)
                .build();
        return ResponseEntity.ok().body(commonResponse);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponseDTO<Void>> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        todoService.deleteTodo(todoId, dto.getPassword());
        CommonResponseDTO<Void> commonResponse = CommonResponseDTO.<Void>builder()
                .statusCode(200)
                .msg("생성 완료")
                .build();
        return ResponseEntity.ok().body(commonResponse);
    }
}