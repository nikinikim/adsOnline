package services;

import DTOs.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdsService {
    ResponsesWrapperAdsDTO getAllAds();
    AdsDTO getAdById(int id);
    AdsDTO createAd(AdsDTO ad);
    AdsDTO updateAd(int id, CreateAdsDTO ad);
    void removeAd(int id);

    AdsDTO addAd(MultipartFile image, CreateAdsDTO createAds);

    FullAdsDTO getFullAdById(Long id);

    List<CommentDTO> getCommentsForAd(int adId);
}
