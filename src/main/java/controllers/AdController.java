package controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    // Получение списка всех объявлений
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        List<Ads> adsList = adService.getAllAds();
        int totalAds = adsList.size();
        ResponseWrapperAds response = new ResponseWrapperAds(totalAds, adsList);
        return ResponseEntity.ok(response);
    }

    // Добавление нового объявления
    @PostMapping
    public ResponseEntity<Ads> addAds(@RequestPart("image") MultipartFile image,
                                      @RequestPart("properties") CreateAds createAds) {
        Ads newAd = adService.addAd(image, createAds);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAd);
    }

    // Получение информации о конкретном объявлении
    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getFullAd(@PathVariable int id) {
        FullAds fullAd = adService.getFullAdById(id);
        return ResponseEntity.ok(fullAd);
    }

    // Обновление информации о конкретном объявлении
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAds(@PathVariable int id, @RequestBody CreateAds updateAds) {
        Ads updatedAd = adService.updateAd(id, updateAds);
        return ResponseEntity.ok(updatedAd);
    }

    // Удаление конкретного объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAds(@PathVariable int id) {
        adService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    // Получение списка комментариев для конкретного объявления
    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("ad_pk") String adId) {
        List<Comment> comments = adService.getCommentsForAd(adId);
        int totalComments = comments.size();
        ResponseWrapperComment response = new ResponseWrapperComment(totalComments, comments);
        return ResponseEntity.ok(response);
    }

    // Добавление комментария к объявлению
    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<Comment> addComments(@PathVariable("ad_pk") String adId,
                                               @RequestBody Comment comment) {
        Comment newComment = adService.addComment(adId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    // Получение информации о конкретном комментарии
    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> getComments(@PathVariable("ad_pk") String adId,
                                               @PathVariable int id) {
        Comment comment = adService.getCommentById(adId, id);
        return ResponseEntity.ok(comment);
    }

    // Обновление информации о конкретном комментарии
    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> updateComments(@PathVariable("ad_pk") String adId,
                                                  @PathVariable int id,
                                                  @RequestBody Comment commentUpdate) {
        Comment updatedComment = adService.updateComment(adId, id, commentUpdate);
        return ResponseEntity.ok(updatedComment);
    }

    // Удаление конкретного комментария
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable("ad_pk") String adId,
                                               @PathVariable int id) {
        adService.deleteComment(adId, id);
        return ResponseEntity.ok().build();
    }
}
