package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.Comment;
import com.example.adsonline.entity.Users;
import com.example.adsonline.exception.NotFoundInDataBaseException;
import com.example.adsonline.mappers.CommentMapper;
import com.example.adsonline.repository.CommentRepository;
import com.example.adsonline.services.UserService;
import com.example.adsonline.services.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentServiceImplTest {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private UserService userService;
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        commentMapper = mock(CommentMapper.class);
        userService = mock(UserService.class);
        commentService = new CommentServiceImpl(commentRepository, commentMapper, userService);
    }

    @Test
    void getCommentsByAdId() {
        int adId = 1;
        List<Comment> commentList = new ArrayList<>();
        when(commentRepository.findAllByAds_Id(adId)).thenReturn(commentList);
        when(commentMapper.toDto(Mockito.any(Comment.class))).thenReturn(new CommentDTO());

        List<CommentDTO> result = commentService.getCommentsByAdId(adId);

        assertNotNull(result);
        assertEquals(commentList.size(), result.size());
    }

    @Test
    void addComment() {
        int adId = 1;
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText ("Test Comment");

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        Users user = new Users();
        user.setId(1);
        when(userService.findUserByLogin("username")).thenReturn(user);

        Comment comment = new Comment();
        comment.setId(1);
        when(commentMapper.fromDto(commentDTO)).thenReturn(comment);
        when(commentRepository.saveAndFlush(comment)).thenReturn(comment);
        when(commentMapper.toDto(comment)).thenReturn(commentDTO);

        CommentDTO result = commentService.addComment(adId, commentDTO, principal);

        assertNotNull(result);
        assertEquals(commentDTO.getText(), result.getText());
    }

    @Test
    void updateComment() {
        int adId = 1;
        long commentId = 1;
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText("Updated Comment");

        Comment existingComment = new Comment();
        existingComment.setId((int) commentId);
        existingComment.setText("Old Comment");

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(commentMapper.fromDto(commentDTO)).thenReturn(existingComment);
        when(commentRepository.saveAndFlush(existingComment)).thenReturn(existingComment);
        when(commentMapper.toDto(existingComment)).thenReturn(commentDTO);

        CommentDTO result = commentService.updateComment(adId, commentId, commentDTO);

        assertNotNull(result);
        assertEquals(commentDTO.getText(), result.getText());
    }

    @Test
    void deleteComment() {
        long idAds = 1;
        long commentId = 1;

        Comment existingComment = new Comment();
        existingComment.setId((int) commentId);
        existingComment.setAds(new Ads());

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        assertDoesNotThrow(() -> commentService.deleteComment(idAds, commentId));

        // Verify that deleteById is called with the correct commentId
        Mockito.verify(commentRepository).deleteById(commentId);
    }

    @Test
    void deleteComment_InvalidCommentId() {
        long idAds = 1;
        long commentId = 1;

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundInDataBaseException.class, () -> commentService.deleteComment(idAds, commentId));
    }

    @Test
    void deleteComment_InvalidAdsId() {
        long idAds = 1;
        long commentId = 1;

        Comment existingComment = new Comment();
        existingComment.setId((int) commentId);
        existingComment.setAds(new Ads());

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        assertThrows(IllegalArgumentException.class, () -> commentService.deleteComment(idAds, commentId));
    }

    @Test
    void getCommentById() {
        long idAds = 1;
        long commentId = 1;

        Comment existingComment = new Comment();
        existingComment.setId((int) commentId);
        existingComment.setAds(new Ads());

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(commentMapper.toDto(existingComment)).thenReturn(new CommentDTO());

        CommentDTO result = commentService.getCommentById(idAds, commentId);

        assertNotNull(result);
    }

    @Test
    void getCommentById_InvalidCommentId() {
        long idAds = 1;
        long commentId = 1;

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundInDataBaseException.class, () -> commentService.getCommentById(idAds, commentId));
    }

    @Test
    void getCommentById_InvalidAdsId() {
        long idAds = 1;
        long commentId = 1;

        Comment existingComment = new Comment();
        existingComment.setId((int) commentId);
        existingComment.setAds(new Ads());

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        assertThrows(IllegalArgumentException.class, () -> commentService.getCommentById(idAds, commentId));
    }
}