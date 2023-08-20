package com.example.adsonline.services;

import com.example.adsonline.DTOs.AdsDTO;
import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.DTOs.CreateAdsDTO;
import com.example.adsonline.DTOs.FullAdsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {
    List<AdsDTO> getAllAds();
    AdsDTO getAdById(int id);
    AdsDTO createAd(AdsDTO ad);
    AdsDTO updateAd(int id, CreateAdsDTO ad);
    void removeAd(int id);

    AdsDTO addAd(MultipartFile image, CreateAdsDTO createAds);

    FullAdsDTO getFullAdById(int id);

    List<CommentDTO> getCommentsForAd(int adId);
}
