package services;

import DTOs.AdsDTO;
import DTOs.Comment;
import DTOs.CreateAds;
import DTOs.FullAds;
import entity.Ads;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {
    List<AdsDTO> getAllAds();
    AdsDTO getAdById(int id);
    AdsDTO createAd(AdsDTO ad);
    AdsDTO updateAd(int id, CreateAds ad);
    void removeAd(int id);

    AdsDTO addAd(MultipartFile image, CreateAds createAds);

    FullAds getFullAdById(int id);

    List<Comment> getCommentsForAd(int adId);

    AdsDTO addToDTO(Ads ads);
    Ads addToEntity(AdsDTO adsDTO);
}
