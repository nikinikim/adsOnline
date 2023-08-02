package controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.ImageService;


@RestController
@RequestMapping("/images")
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Обновление изображения объявления по идентификатору
    @PatchMapping("/{id}")
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable int id, @RequestParam("image") MultipartFile image) {
        byte[] updatedImage = imageService.updateAdImages(id, image);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(updatedImage);
    }
}
