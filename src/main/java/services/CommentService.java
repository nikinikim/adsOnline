package services;

import DTOs.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByAdId(int adId);
    Comment addComment(int adId, Comment comment);
    Comment updateComment(int adId, int commentId, Comment comment);
    void deleteComment(int adId, int commentId);
}
