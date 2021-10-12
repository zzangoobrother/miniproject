package com.miniproject.spring.service;

import com.miniproject.spring.dto.SignUpRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.User;
import com.miniproject.spring.model.UserRoleEnum;
import com.miniproject.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void registerUser(SignUpRequestDto requestDto) throws HanghaeMiniException {
        // 패스워드 암호화
        String pw = passwordEncoder.encode(requestDto.getPw());
        //사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new HanghaeMiniException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(requestDto.getEmail(), pw, requestDto.getNickname(), role);
        userRepository.save(user);
    }

    public User login(String email) throws HanghaeMiniException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new HanghaeMiniException("이메일을 찾을 수 없습니다.")
        );
    }
}





