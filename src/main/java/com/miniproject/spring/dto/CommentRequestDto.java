package com.miniproject.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {
    private String nickname;
    private String comment;
}
