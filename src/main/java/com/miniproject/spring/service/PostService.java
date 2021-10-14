package com.miniproject.spring.service;

import com.miniproject.spring.dto.PostRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.Comment;
import com.miniproject.spring.model.Post;
import com.miniproject.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    //수정
    @Transactional
    public Post updatePost(Long id, PostRequestDto postRequestDto) throws HanghaeMiniException {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new HanghaeMiniException("존재하지않습니다.")
        );
        post.update(postRequestDto);
        return post;

    }

    //작성
    public String createPost(PostRequestDto postRequestDto) {
        try {
            Post post = new Post(postRequestDto);
            post.setNickname("test");
            postRepository.save(post).getNickname();
            return "success";
        } catch (Exception e)
        {
            return "fail";
        }
    }

    //삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> home() {
        return postRepository.findAllByOrderByModifiedDt();
    }

    public Post getPosts(Long id) throws HanghaeMiniException {
        return postRepository.findById(id).orElseThrow(
                () -> new HanghaeMiniException("게시글을 찾을 수 없습니다.")
        );
    }
}



