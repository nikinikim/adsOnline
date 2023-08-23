package services.impl;

import DTOs.CommentDTO;
import mappers.CommentMapper;
import services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

//    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
//        this.commentRepository = commentRepository;
//        this.commentMapper = commentMapper;
//    }

//    @Override
//    public CommentDTO mapToDTO(Comment comment) {
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setPk(comment.getPk());
//        commentDTO.setAuthor(comment.getAuthor());
//        commentDTO.setText(comment.getText());
//        commentDTO.setCreatedAt(comment.getCreatedAt());
//
//        return commentDTO;
//    }
//
//    @Override
//    public Comment mapToEntity(CommentDTO commentDTO) {
//        Comment comment = new Comment();
//        comment.setPk(commentDTO.getPk());
//        comment.setAuthor(commentDTO.getAuthor());
//        comment.setText(commentDTO.getText());
//        comment.setCreatedAt(commentDTO.getCreatedAt());
//
//        return comment;
//    }

    @Override
    public List<CommentDTO> getCommentsByAdId(int adId) {
        return commentRepository.findAllByAd_Id(adId).stream().map(commentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(int adId, CommentDTO comment) {
        commentRepository.saveAndFlush(commentMapper.fromDto(comment));
        return comment;
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
