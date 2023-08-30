package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.entity.Comment;
import com.example.adsonline.exception.NotFoundInDataBaseException;
import com.example.adsonline.mappers.CommentMapper;
import com.example.adsonline.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.adsonline.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

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
    public CommentDTO updateComment(int adId, long commentId, CommentDTO comment) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundInDataBaseException("Comment not found"));

        throw new IllegalArgumentException("Comment does not belong to the specified ad");

    }

    @Override
    public void deleteComment(long IdAds, long commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundInDataBaseException("Comment not found"));

        if (existingComment.getAd().getIdAds() != IdAds) {
            throw new IllegalArgumentException("Comment does not belong to the specified ad");
        }

        commentRepository.delete(existingComment);


    }

    @Override
    public CommentDTO getCommentById(long IdAds, long commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundInDataBaseException("Comment not found"));

        if (existingComment.getAd().getIdAds() != IdAds) {
            throw new IllegalArgumentException("Comment does not belong to the specified ad");
        }

        return commentMapper.toDto(existingComment);
    }
}
