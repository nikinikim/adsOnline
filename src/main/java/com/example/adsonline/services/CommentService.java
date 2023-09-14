package com.example.adsonline.services;

import com.example.adsonline.DTOs.CommentDTO;
import java.security.Principal;
import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsByAdId(int adId);
    CommentDTO addComment(int adId, CommentDTO comment, Principal principal);

    CommentDTO updateComment(int adId, long commentId, CommentDTO comment);

    void deleteComment(long IdAds, long commentId);

    CommentDTO getCommentById(long IdAds, long commentId);
}
