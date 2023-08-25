package com.example.adsonline.repository;

import com.example.adsonline.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
        List<Comment> findAllByAd_Id(Integer ad_id);
}
