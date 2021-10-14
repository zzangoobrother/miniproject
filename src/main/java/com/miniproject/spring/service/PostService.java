package com.miniproject.spring.service;

import com.miniproject.spring.dto.PostRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.Post;
import com.miniproject.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
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
            post.setNickname("");
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

    public Page<Post> home(Pageable pageable) {

        return postRepository.findAllByOrderByModifiedDt(pageable);
    }

    public Post getPosts(Long id) throws HanghaeMiniException {
        return postRepository.findById(id).orElseThrow(
                () -> new HanghaeMiniException("게시글을 찾을 수 없습니다.")
        );
    }


}




