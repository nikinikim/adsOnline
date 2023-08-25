package com.example.adsonline.services;

import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.entity.Comment;

import java.util.List;

public interface CommentService {
    //CommentDTO mapToDTO(Comment comment);

    //Comment mapToEntity(CommentDTO commentDTO);

    List<CommentDTO> getCommentsByAdId(int adId);
    CommentDTO addComment(int adId, CommentDTO comment);
    CommentDTO updateComment(int adId, int commentId, CommentDTO comment);
    void deleteComment(int adId, int commentId);

    CommentDTO getCommentById(int adId, int commentId);
}
