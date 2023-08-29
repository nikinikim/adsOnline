package com.example.adsonline.services;

import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.entity.Comment;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsByAdId(int adId);
    CommentDTO addComment(int adId, CommentDTO comment);

    CommentDTO updateComment(int adId, long commentId, CommentDTO comment);

    void deleteComment(long IdAds, long commentId);

    CommentDTO getCommentById(long IdAds, long commentId);
}
