package com.miniproject.spring.service;

import com.miniproject.spring.dto.SignUpRequestDto;
import com.miniproject.spring.dto.UserRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.User;
import com.miniproject.spring.model.UserRoleEnum;
import com.miniproject.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //가입
    public void createUser(SignUpRequestDto requestDto) throws HanghaeMiniException {
        registerUser(requestDto);
        User user = new User(requestDto);
        userRepository.save(user);
    }

    private void registerUser(SignUpRequestDto requestDto) throws HanghaeMiniException {

        // 패스워드 암호화
        String pw = passwordEncoder.encode(requestDto.getPw());


        //email(id) 중복체크
        String email = requestDto.getEmail();
        Optional<User> found = userRepository.findByEmail(email);
        System.out.println(email);
        if (found.isPresent()){
            throw new HanghaeMiniException("중복된 email이 존재합니다.");
        }


        //nickname 중복체크
        String nickname = requestDto.getNickname();
        Optional<User> found2 = userRepository.findByNickname(nickname);
        if (found2.isPresent()) {
            throw new HanghaeMiniException("중복된 닉네임이 존재합니다.");
        }

        //비밀번호확인



        //사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

    }

    public User login(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("이메일을 찾을 수 없습니다.")
        );
    }


}



