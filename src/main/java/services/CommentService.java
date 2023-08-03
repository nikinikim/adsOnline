package services;

import DTOs.CommentDTO;
import entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDTO mapToDTO(Comment comment);

    Comment mapToEntity(CommentDTO commentDTO);

    List<CommentDTO> getCommentsByAdId(int adId);
    CommentDTO addComment(int adId, CommentDTO comment);
    CommentDTO updateComment(int adId, int commentId, CommentDTO comment);
    void deleteComment(int adId, int commentId);

    CommentDTO getCommentById(int adId, int commentId);
}
