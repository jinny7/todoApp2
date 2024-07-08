package com.jinny7.todoapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "todo")
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todoId", nullable = false)
    private Long todoId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Builder
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Getter
    @Setter
    @Entity
    @Table(name = "comment")
    @NoArgsConstructor
    public static class Comment {
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
}