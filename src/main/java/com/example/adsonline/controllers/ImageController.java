package com.example.adsonline.controllers;

import com.example.adsonline.services.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Изображения", description = "Изображения")
public class ImageController {

    private final ImageService imageService;

    // Обновление изображения объявления по идентификатору
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> updateAdsImage(@PathVariable int id, @RequestParam MultipartFile image) {

        imageService.updateAdImages(id, image);

        return ResponseEntity.ok(List.of(String.format("/images-ads/%s/", id,image.getOriginalFilename())));//.contentType(MediaType.APPLICATION_OCTET_STREAM).body(updatedImage);
    }
}
