package com.example.adsonline.services.impl;

import com.example.adsonline.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] updateAdImages(int adId, MultipartFile image) throws IOException {

        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Изображение отсутствует или пустое.");
        }

        byte[] imageData = image.getBytes();

        return imageData;
    }
}
