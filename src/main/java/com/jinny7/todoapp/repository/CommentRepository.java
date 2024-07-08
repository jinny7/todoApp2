package com.jinny7.todoapp.repository;

import com.jinny7.todoapp.entity.Comment;
import com.jinny7.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTodo(Todo todo);
}
