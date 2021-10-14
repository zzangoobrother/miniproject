package com.miniproject.spring.controller;

import com.miniproject.spring.model.Post;
import com.miniproject.spring.service.CommentService;
import com.miniproject.spring.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    private final PostService postService;
    private final CommentService commentService;

    public HomeController(PostService postService, CommentService commentService) {

        this.postService = postService;
        this.commentService = commentService;
    }

    // 메인페이지 게시글 전체 조회
    @GetMapping("/posts/{page}")
    public Map<String, Object> home(@PathVariable int page, @PageableDefault(page = 0,
            size = 10, sort = "modifiedDt", direction = Sort.Direction.DESC) Pageable pageable) {
        Pageable pageable1 = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
        Page<Post> posts = postService.home(pageable1);
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);

        return result;
    }

    // 카테고리별 게시글 조회
    @GetMapping("/posts/{category}/{page}")
    public Map<String, Object> getPostsCategory(@PathVariable String category, @PathVariable int page, @PageableDefault(page = 0,
            size = 10, sort = "modifiedDt", direction = Sort.Direction.DESC) Pageable pageable) {
        Pageable pageable1 = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
        Page<Post> posts = postService.getPostsCategory(category, pageable1);

        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);

        return result;
    }
}
