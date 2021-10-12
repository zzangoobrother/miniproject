package com.miniproject.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miniproject.spring.model.User;
import com.miniproject.spring.service.KakaoUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserKakaoController {

    private final KakaoUserService kakaoUserService;

    public UserKakaoController(KakaoUserService kakaoUserService) {
        this.kakaoUserService = kakaoUserService;
    }

    //카카오
    @GetMapping("/kakao/callback")
    public User kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(code);
    }
}
