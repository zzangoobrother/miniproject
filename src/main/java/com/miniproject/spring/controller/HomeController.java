package com.miniproject.spring.controller;

import com.miniproject.spring.model.Post;
import com.miniproject.spring.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    // 메인페이지 게시글 전체 조회
    @GetMapping("/posts")
    public Map<String, Object> home() {
        List<Post> posts = postService.home();
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);

        return result;
    }
}
