package com.example.adsonline.controller;

import com.example.adsonline.DTOs.*;
import com.example.adsonline.config.WebSecurityConfig;
import com.example.adsonline.controllers.AdsController;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.Comment;
import com.example.adsonline.services.AdsService;
import com.example.adsonline.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {WebSecurityConfig.class})
public class AdsControllerTest {

    @InjectMocks
    AdsController adsController;
    @Mock
    private AdsService adsService;
    @Mock
    private CommentService commentService;
    @Mock
    private final Ads testAds = new Ads();
    private final Comment testCom = new Comment();
    private final CommentDTO commentDTO = new CommentDTO();
    private final CreateAdsDTO createAdsDTO = new CreateAdsDTO();

    @BeforeEach
    public void setUp() {
        testAds.setId(1);
        testAds.setDescription("test description");
        testAds.setTitle("test title");
        testAds.setPrice(50);

        testCom.setId(2);

        commentDTO.setPk(1);
        commentDTO.setText("test text");

        createAdsDTO.setDescription("New Description");
        createAdsDTO.setTitle("New Title");
        createAdsDTO.setPrice(500);
    }

    @Test
    public void getAllAdsTest() {
        List<AdsDTO> adsDTOList = Collections.singletonList(new AdsDTO());
        ResponsesWrapperAdsDTO adsDTO = new ResponsesWrapperAdsDTO();

        when(adsService.getAllAds()).thenReturn(adsDTO);

        ResponseEntity<ResponsesWrapperAdsDTO> responseEntity = adsController.getAllAds();

        verify(adsService).getAllAds();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void addAdTest() throws Exception {
        AdsDTO adsDTO = new AdsDTO();
        MultipartFile image = new MockMultipartFile("test.jpg", "test.jpg",
                "image/jpeg", "test image".getBytes());

        when(adsService.createAds("", (Principal) image)).thenReturn(adsDTO);

        ResponseEntity<AdsDTO> response = adsController.updateAds(1, (CreateAdsDTO) image);

        verify(adsService).createAds("createAdsDTO", (Principal) image);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDTO, response.getBody());
        assertNotNull(adsDTO);
    }

    @Test
    public void getCommentsTest() {
        List<CommentDTO> commentDtoList = Collections.singletonList(new CommentDTO());
        ResponseWrapperCommentDTO comments = new ResponseWrapperCommentDTO();

        when(commentService.getCommentsByAdId(testAds.getId())).thenReturn(commentDtoList);

        ResponseEntity<ResponseWrapperCommentDTO> response = adsController.getCommentsForAd(testAds.getId());

        verify(commentService).getCommentsByAdId(testAds.getId());

        assertEquals(comments, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void removeAdTest() {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setTitle("New Title");
        adsDTO.setPrice(500);
        when(adsService.updateAdsDTO(testAds.getId(), createAdsDTO)).thenReturn(adsDTO);

        ResponseEntity<?> response = adsController.removeAd(testAds.getId());

        verify(adsService).deleteAdsDTO(testAds.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void updateAdTest() {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setTitle("New Title");
        adsDTO.setPrice(500);

        when(adsService.updateAdsDTO(testAds.getId(), createAdsDTO)).thenReturn(adsDTO);

        ResponseEntity<AdsDTO> response = adsController.updateAds(testAds.getId(), createAdsDTO);

        verify(adsService).updateAdsDTO(testAds.getId(), createAdsDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createAdsDTO.getTitle(), Objects.requireNonNull(response.getBody()).getTitle());
        assertEquals(createAdsDTO.getPrice(), response.getBody().getPrice());
    }

    @Test
    public void deleteCommentWithOkTest() {
        CommentDTO newCommentDto = new CommentDTO();
        newCommentDto.setPk(1);
        newCommentDto.setText("test text");
        when(commentService.updateComment(testAds.getId(), testCom.getId(), commentDTO)).thenReturn(newCommentDto);

        ResponseEntity<Void> response = adsController.deleteComment(testAds.getId(), testCom.getId());

        verify(commentService).deleteComment(testAds.getId(), testCom.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateCommentTest() {
        CommentDTO newCommentDto = new CommentDTO();
        newCommentDto.setPk(1);
        newCommentDto.setText("test text");

        when(commentService.updateComment(testAds.getId(), testCom.getId(), commentDTO)).thenReturn(newCommentDto);

        ResponseEntity<CommentDTO> response = adsController.updateComment(testAds.getId(), testCom.getId(), commentDTO);

        verify(commentService).updateComment(testAds.getId(), testCom.getId(), commentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newCommentDto, response.getBody());
        assertEquals(commentDTO.getPk(), Objects.requireNonNull(response.getBody()).getPk());
        assertEquals(commentDTO.getText(), response.getBody().getText());
        assertEquals(commentDTO.getAuthor(), response.getBody().getAuthor());
    }

    @Test
    public void getAdsMeTest() {
        List<AdsDTO> adsDTOList = Collections.singletonList(new AdsDTO());
        ResponsesWrapperAdsDTO adsDTO = new ResponsesWrapperAdsDTO();

        when(adsService.updateAdsDTO(testAds.getId(), createAdsDTO)).thenReturn((AdsDTO) adsDTOList);

        ResponseEntity<ResponsesWrapperAdsDTO> response = adsController.getAllAds();

        verify(adsService).getFullAdsDTO(testAds.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adsDTO, response.getBody());
    }

}

