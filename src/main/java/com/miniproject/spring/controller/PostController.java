package com.miniproject.spring.controller;

import com.miniproject.spring.dto.PostRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.Comment;
import com.miniproject.spring.model.Post;
import com.miniproject.spring.service.CommentService;
import com.miniproject.spring.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // 게시글 조회
    @GetMapping("/posts/{id}")
    public Map<String, Object> getPosts(@PathVariable Long id) throws HanghaeMiniException {
        Post post = postService.getPosts(id);
        Map<String, Object> result = new HashMap<>();

        result.put("title", post.getTitle());
        result.put("author", post.getAuthor());
        result.put("contents", post.getContents());
        result.put("insertDt", post.getInsertDt());
        result.put("nickname", post.getNickname());

        List<Comment> comments = commentService.getComments();
        result.put("comments", comments);

        return result;
    }

    //게시물 작성
    @PostMapping("/posts")
    public Map<String, String> createPost(@RequestBody PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;

    }


    //게시물 수정
    @PutMapping("/posts/{id}")
    public Map<String, Object> update( @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) throws HanghaeMiniException {
        postService.update(id, postRequestDto);
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }


    //게시물 삭제
    @DeleteMapping("/posts/{id}")
    public Map<String, Object> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }
}
