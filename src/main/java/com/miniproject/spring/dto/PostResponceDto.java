package com.miniproject.spring.dto;

import com.miniproject.spring.model.Comment;
import com.miniproject.spring.model.PostCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostResponceDto {
    private Long id;
    private PostCategoryEnum category;
    private String title;
    private String author;
    private String nickname;
    private String contents;
    private List<Comment> comments;
    private LocalDateTime insertDt;
    private LocalDateTime modifiedDt;
}
