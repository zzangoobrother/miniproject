package com.miniproject.spring.repository;

import com.miniproject.spring.dto.PostRequestDto;
import com.miniproject.spring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByModifiedDt();


}