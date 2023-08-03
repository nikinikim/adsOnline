package controllers;

import DTOs.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.AdService;
import services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdController {

    private final AdService adService;
    private final CommentService commentService;

    public AdController(AdService adService, CommentService commentService) {

        this.adService = adService;
        this.commentService = commentService;
    }

    // Получение списка всех объявлений
    @GetMapping
    public ResponseEntity<ResponsesWrapperAdsDTO> getAllAds() {

        List<AdsDTO> adsList = adService.getAllAds();
        int totalAds = adsList.size();
        ResponsesWrapperAdsDTO response = new ResponsesWrapperAdsDTO();
        return ResponseEntity.ok(response);
    }

    // Добавление нового объявления
    @PostMapping
    public ResponseEntity<AdsDTO> addAds(@RequestPart("image") MultipartFile image,
                                         @RequestPart("properties") CreateAdsDTO createAds) {

        AdsDTO newAd = adService.addAd(image, createAds);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAd);
    }

    // Получение информации о конкретном объявлении
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDTO> getFullAd(@PathVariable int id) {

        FullAdsDTO fullAd = adService.getFullAdById(id);
        return ResponseEntity.ok(fullAd);
    }

    // Обновление информации о конкретном объявлении
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDTO> updateAds(@PathVariable int id, @RequestBody CreateAdsDTO updateAds) {

        AdsDTO updatedAd = adService.updateAd(id, updateAds);
        return ResponseEntity.ok(updatedAd);
    }

    // Удаление конкретного объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable int id) {

        adService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    // Получение списка комментариев для конкретного объявления
    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperCommentDTO> getComments(@PathVariable("ad_pk") int adId) {

        List<CommentDTO> comments = adService.getCommentsForAd(adId);
        int totalComments = comments.size();
        ResponseWrapperCommentDTO response = new ResponseWrapperCommentDTO();
        return ResponseEntity.ok(response);
    }

    // Добавление комментария к объявлению
    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<CommentDTO> addComments(@PathVariable("ad_pk") int adId,
                                                  @RequestBody CommentDTO comment) {

        CommentDTO newComment = commentService.addComment(adId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    // Получение информации о конкретном комментарии
    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDTO> getComments(@PathVariable("ad_pk") int adId,
                                                  @PathVariable int id) {

        CommentDTO comment = commentService.getCommentById(adId, id);
        return ResponseEntity.ok(comment);
    }

    // Обновление информации о конкретном комментарии
    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComments(@PathVariable("ad_pk") int adId,
                                                     @PathVariable int id,
                                                     @RequestBody CommentDTO commentUpdate) {

        CommentDTO updatedComment = commentService.updateComment(adId, id, commentUpdate);
        return ResponseEntity.ok(updatedComment);
    }

    // Удаление конкретного комментария
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable("ad_pk") int adId,
                                               @PathVariable int id) {

        commentService.deleteComment(adId, id);
        return ResponseEntity.ok().build();
    }
}
