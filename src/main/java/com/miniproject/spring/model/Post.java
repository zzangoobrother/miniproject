package com.miniproject.spring.model;

import com.miniproject.spring.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    public Post(String title, String author, String nickname, String contents) {
        this.title = title;
        this.author = author;
        this.nickname = nickname;
        this.contents = contents;
    }

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }
}
