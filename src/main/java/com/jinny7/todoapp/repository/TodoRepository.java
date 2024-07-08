package com.jinny7.todoapp.repository;

import com.jinny7.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
