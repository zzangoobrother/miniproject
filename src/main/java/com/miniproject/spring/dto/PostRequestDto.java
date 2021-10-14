package com.miniproject.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequestDto {
    private Long id;
    private String category;
    private String title;
    private String contents;
    private String author;
}
