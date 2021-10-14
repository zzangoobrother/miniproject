package com.miniproject.spring.controller;

import com.miniproject.spring.model.Comment;
import com.miniproject.spring.model.Post;
import com.miniproject.spring.service.CommentService;
import com.miniproject.spring.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @GetMapping("/posts")
    public Map<String, Object> home(@PageableDefault(page = 0,
            size = 10, sort = "modifiedDt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> posts = postService.home(pageable);
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);

        return result;
    }

    // 카테고리별 게시글 조회
    @GetMapping("/posts/{category}")
    public Map<String, Object> getPostsCategory(@PathVariable String category) {
        List<Post> posts = postService.getPostsCategory(category);

        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);

        return result;
    }
}
