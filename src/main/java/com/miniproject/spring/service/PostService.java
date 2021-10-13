package com.miniproject.spring.service;

import com.miniproject.spring.dto.PostRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
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
    public Long update(Long id, PostRequestDto postRequestDto) throws HanghaeMiniException {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new HanghaeMiniException("존재하지않습니다.")
        );
        post.update(postRequestDto);
        return post.getId();

    }

    //작성
    @Transactional
    public String createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        return postRepository.save(post).getNickname();
    }

    //삭제
    @Transactional
    public Long deletePost(Long id)  {
        postRepository.deleteById(id);

        return id;
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



