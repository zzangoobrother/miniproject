package com.miniproject.spring.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDto {
    private String email;
    private String pw;
    private String pwCheck;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";

}
