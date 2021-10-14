package com.miniproject.spring.service;

import com.miniproject.spring.dto.PostRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.Post;
import com.miniproject.spring.model.PostCategoryEnum;
import com.miniproject.spring.model.User;
import com.miniproject.spring.repository.PostRepository;
import com.miniproject.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

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
    public Map<String, Object> createPost(PostRequestDto postRequestDto) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userRepository.findByEmail(postRequestDto.getAuthor()).orElseThrow(null);

            PostCategoryEnum category = categorychoose(postRequestDto.getCategory());

            Post post = new Post(postRequestDto.getTitle(), category, postRequestDto.getAuthor(), user.getNickname(), postRequestDto.getContents());
            Post savePost = postRepository.save(post);

            result.put("post", savePost);
            result.put("result", "success");
        } catch (Exception e)
        {
            result.put("result", "fail");
        }

        return result;
    }

    private PostCategoryEnum categorychoose(String category) {
        if (category.equalsIgnoreCase("react")) {
            return PostCategoryEnum.REACT;
        }
        if (category.equalsIgnoreCase("spring")) {
            return PostCategoryEnum.SPRING;
        }

        return PostCategoryEnum.NODE;
    }

    //삭제
    @Transactional
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

    public List<Post> getPostsCategory(String category) {
        PostCategoryEnum categoryEnum = categorychoose(category);
        return postRepository.findAllByCategoryOrderByModifiedDtDesc(categoryEnum);
    }
}



