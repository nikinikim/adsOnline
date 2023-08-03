package services.impl;

import DTOs.CommentDTO;
import entity.Comment;
import org.springframework.stereotype.Service;
import repository.CommentRepository;
import services.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDTO mapToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPk(comment.getPk());
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setText(comment.getText());
        commentDTO.setCreatedAt(comment.getCreatedAt());

        return commentDTO;
    }

    @Override
    public Comment mapToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setPk(commentDTO.getPk());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setText(commentDTO.getText());
        comment.setCreatedAt(commentDTO.getCreatedAt());

        return comment;
    }

    @Override
    public List<CommentDTO> getCommentsByAdId(int adId) {
        return new ArrayList<>();
    }

    @Override
    public CommentDTO addComment(int adId, CommentDTO comment) {
        return new CommentDTO();
    }

    @Override
    public CommentDTO updateComment(int adId, int commentId, CommentDTO comment) {
        return new CommentDTO();
    }

    @Override
    public void deleteComment(int adId, int commentId) {

    }

    @Override
    public CommentDTO getCommentById(int adId, int commentId) {
        // Тестовые данные для заглушки

        CommentDTO comment = new CommentDTO();
        comment.setAuthor(1);
        comment.setCreatedAt("2023-07-27");
        comment.setPk(1);
        comment.setText("Test comment");

        return comment;
    }
}
