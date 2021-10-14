package com.miniproject.spring.model;

import com.miniproject.spring.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Post extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PostCategoryEnum category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    public Post(String title, PostCategoryEnum category, String author, String nickname, String contents) {
        this.title = title;
        this.category = category;
        this.author = author;
        this.nickname = nickname;
        this.contents = contents;
    }

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.author = postRequestDto.getAuthor();
        this.nickname = postRequestDto.getAuthor();
    }

    public void update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }
}
