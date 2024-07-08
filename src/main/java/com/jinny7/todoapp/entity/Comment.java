package com.jinny7.todoapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId", nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "todoId", nullable = false)
    private Todo todo;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Comment(String content, Todo todo, String userName) {
        this.content = content;
        this.todo = todo;
        this.userName = userName;
        this.createdAt = LocalDateTime.now();
    }
}
