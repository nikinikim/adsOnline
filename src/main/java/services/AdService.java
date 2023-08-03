package services;

import DTOs.Ads;
import DTOs.CommentDTO;
import DTOs.CreateAds;
import DTOs.FullAds;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {
    List<Ads> getAllAds();
    Ads getAdById(int id);
    Ads createAd(Ads ad);
    Ads updateAd(int id, CreateAds ad);
    void removeAd(int id);

    Ads addAd(MultipartFile image, CreateAds createAds);

    FullAds getFullAdById(int id);

    List<CommentDTO> getCommentsForAd(int adId);
}
