package com.miniproject.spring.repository;

import com.miniproject.spring.model.Comment;
import com.miniproject.spring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostOrderByModifiedDtDesc(Post post);
    void deleteByPost(Post post);
}
