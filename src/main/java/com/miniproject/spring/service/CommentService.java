package com.miniproject.spring.service;

import com.miniproject.spring.dto.CommentRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.Comment;
import com.miniproject.spring.model.Post;
import com.miniproject.spring.repository.CommentRepository;
import com.miniproject.spring.repository.PostRepository;
import com.miniproject.spring.security.UserDetailsImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Transactional
    public Comment updateComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElse(null);
        comment.update(commentRequestDto);
        return comment;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public Comment createComment(CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) throws HanghaeMiniException {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new HanghaeMiniException("해당 게시글을 찾을 수 없습니다.")
        );

        String nickname = "test 확인";
        if (userDetails != null) {
            nickname = userDetails.getUsername();
        }
        Comment comment = new Comment(nickname, commentRequestDto.getComment(), post);
        Comment saveComment = commentRepository.save(comment);

        return saveComment;
    }
}
//test