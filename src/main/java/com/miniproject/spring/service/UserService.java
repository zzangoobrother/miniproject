package com.miniproject.spring.service;

import com.miniproject.spring.dto.SignUpRequestDto;
import com.miniproject.spring.dto.UserRequestDto;
import com.miniproject.spring.exception.HanghaeMiniException;
import com.miniproject.spring.model.User;
import com.miniproject.spring.model.UserRoleEnum;
import com.miniproject.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        //email(id) 중복체크
        String email = requestDto.getEmail();
        Optional<User> found = userRepository.findByEmail(email);
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
        String password = requestDto.getPw();
        String passwordCheck = requestDto.getPwCheck();

        if (!password.isEmpty() && !passwordCheck.isEmpty()) {
            if (password.length() >= 6 && password.length() <= 20) {
                if (!password.equals(passwordCheck)) {
                    throw new HanghaeMiniException("비밀번호가 일치하지 않습니다.");
                }
            } else {
                throw new HanghaeMiniException("비밀번호는 6~20자리로 해주세요");

            }
        } else {
            throw new HanghaeMiniException("비밀번호를 입력해주세요");
        }



        //사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new HanghaeMiniException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(email, pw, nickname, role);
        userRepository.save(user);
    }

    public User login(UserRequestDto requestDto) throws HanghaeMiniException {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new HanghaeMiniException("이메일을 찾을 수 없습니다.")
        );

        // 패스워드 암호화
        if (!passwordEncoder.matches(requestDto.getPw(), user.getPw())) {
            throw new HanghaeMiniException("비밀번호가 맞지 않습니다.");
        }

        return user;
    }


}



