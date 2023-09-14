package com.example.adsonline.services.impl;

import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.Image;
import com.example.adsonline.exception.BusinessLogicException;
import com.example.adsonline.repository.AdsRepository;
import com.example.adsonline.repository.ImageRepository;
import com.example.adsonline.services.FileService;
import com.example.adsonline.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final FileService fileService;
    private final AdsRepository adsRepository;
    @Override
    @Transactional
    public void updateAdImages(int adId, MultipartFile image) {
        if (image == null) {
            throw new BusinessLogicException("Отсутствует файл для загрузки", HttpStatus.BAD_REQUEST);
        }

        Ads ads = new Ads();
        ads.setId(adId);
        String ref;
        try {
            ref = fileService.upload(String.format("images-%s",ads.getClass().getSimpleName()), String.valueOf(adId), image.getOriginalFilename(), image.getBytes());
        } catch (IOException e) {
            throw new BusinessLogicException("Файл не загружен", HttpStatus.BAD_REQUEST);
        }
        Image file = new Image();
        if (imageRepository.existsImageByAds_Id(adId)) {
            file = imageRepository.findImageByAds_Id(adId).orElseThrow();
        }

        file.setAds(ads);
        file.setImageRef(ref);
        imageRepository.saveAndFlush(file);

    }
}
