package com.miniproject.spring.service;

import com.miniproject.spring.model.Comment;
import com.miniproject.spring.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {

        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {

        return commentRepository.findAll();
    }
}
