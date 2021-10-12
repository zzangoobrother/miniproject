package com.miniproject.spring.controller;

import com.miniproject.spring.dto.SignUpRequestDto;
import com.miniproject.spring.model.User;
import com.miniproject.spring.security.jwt.JwtTokenProvider;
import com.miniproject.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    //회원가입 페이지
    @GetMapping("/signup")
    public String signup() {

        return "signup";
    }

    //가입 요청 처리
    @PostMapping("/signup")
    public String registerUser(@RequestBody SignUpRequestDto requestDto, Model model) {

        return "redirect:/";
    }

    // 로그인
    @PostMapping("/user/login")
    public List<Map<String,String>> login(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.login(requestDto.getEmail());

        Map<String,String> username =new HashMap<>();
        Map<String,String>token = new HashMap<>();
        List<Map<String,String>> tu = new ArrayList<>(); // -> 리스트를 만드는데, Map형태(키:밸류 형태)의 변수들을 담을 것이다.
        token.put("token",jwtTokenProvider.createToken(user.getNickname(), user.getEmail())); // "username" : {username}
        username.put("nickname",user.getNickname()); // "token" : {token}
        tu.add(username); //List형태 ["username" : {username}]
        tu.add(token); //List형태 ["token" : {token}]

        return tu;
    }
}