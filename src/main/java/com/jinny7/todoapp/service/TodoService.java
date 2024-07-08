package com.jinny7.todoapp.service;

import com.jinny7.todoapp.dto.TodoRequestDTO;
import com.jinny7.todoapp.entity.Todo;
import com.jinny7.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 할일 생성
    @Transactional
    public Todo createTodo(TodoRequestDTO dto) {
        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);
    }

    // 할일 조회
    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(IllegalAccessError::new);
    }

    // 할일 전체 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }

    // 할일 수정
    @Transactional
    public Todo updateTodo(Long todoId, TodoRequestDTO dto) {
        Todo todo = checkPWAndGetTodo(todoId, dto.getPassword());

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(dto.getUserName());

        return todoRepository.save(todo);
    }

    private Todo checkPWAndGetTodo(Long todoId, String password) {
        Todo todo = getTodo(todoId);
        // 비밀번호 체크
        if (todo.getPassword() != null && !todo.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return todo;
    }

    @Transactional
    public void deleteTodo(Long todoId, String password) {
        Todo todo = checkPWAndGetTodo(todoId, password);
        todoRepository.delete(todo);
    }
}
