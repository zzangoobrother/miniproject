package com.miniproject.spring.model;

import com.miniproject.spring.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    public Comment(String nickname, String comment, Post post) {
        this.nickname = nickname;
        this.comment = comment;
        this.post = post;
    }

    public Comment(CommentRequestDto commentRequestDto) {
        this.nickname = commentRequestDto.getNickname();
        this.comment = commentRequestDto.getComment();
    }

    public void update(CommentRequestDto commentRequestDto) {

        this.comment = commentRequestDto.getComment();
    }
}
