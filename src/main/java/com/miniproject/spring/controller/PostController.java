package com.miniproject.spring.controller;

import com.miniproject.spring.service.PostService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
}
