package com.miniproject.spring.service;

import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.Post;
import com.miniproject.spring.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPosts(Long id) throws HanghaeMiniException {
        return postRepository.findById(id).orElseThrow(
                () -> new HanghaeMiniException("게시글을 찾을 수 없습니다.")
        );
    }
}
