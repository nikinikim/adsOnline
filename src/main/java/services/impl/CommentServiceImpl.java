package services.impl;

import DTOs.Comment;
import org.springframework.stereotype.Service;
import services.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> getCommentsByAdId(int adId) {
        return new ArrayList<>();
    }

    @Override
    public Comment addComment(int adId, Comment comment) {
        return new Comment();
    }

    @Override
    public Comment updateComment(int adId, int commentId, Comment comment) {
        return new Comment();
    }

    @Override
    public void deleteComment(int adId, int commentId) {

    }
}
