package com.jinny7.todoapp.service;

import com.jinny7.todoapp.dto.CommentRequestDTO;
import com.jinny7.todoapp.dto.CommentResponseDTO;
import com.jinny7.todoapp.entity.Comment;
import com.jinny7.todoapp.entity.Todo;
import com.jinny7.todoapp.repository.CommentRepository;
import com.jinny7.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public Comment createComment(CommentRequestDTO dto, String username) {
        if (dto.getTodoId() == null) {
            throw new IllegalArgumentException("ID가 유효하지 않습니다.");
        }
        if (dto.getContent() == null || dto.getContent().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 작성되지 않았습니다.");
        }

        Todo todo = todoRepository.findById(dto.getTodoId())
                .orElseThrow(() -> new IllegalArgumentException("일정 저장에 실패하였습니다."));
        var newComment = dto.toEntity(todo);
        return commentRepository.save(newComment);
    }

    public List<CommentResponseDTO> getCommentsByTodoId(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("ID가 유효하지 않습니다."));
        List<Comment> comments = commentRepository.findByTodo(todo);
        return comments.stream().map(CommentResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Comment updateComment(Long commentId, String content, String userName) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 작성되지 않았습니다.");
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 저장에 실패하였습니다."));

        if (!comment.getUserName().equals(userName)) {
            throw new IllegalArgumentException("선택한 댓글의 작성자가 현재 사용자와 일치하지 않습니다.");
        }

        comment.setContent(content);
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, String userName) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 저장에 실패하였습니다."));

        if (!comment.getUserName().equals(userName)) {
            throw new IllegalArgumentException("선택한 댓글의 작성자가 현재 사용자와 일치하지 않습니다.");
        }

        commentRepository.delete(comment);
    }
}
