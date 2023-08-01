package services;

import DTOs.Ads;
import DTOs.CreateAds;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {
    List<Ads> getAllAds();
    Ads getAdById(int id);
    Ads createAd(Ads ad);
    Ads updateAd(int id, Ads ad);
    void deleteAd(int id);

    Ads addAd(MultipartFile image, CreateAds createAds);

}
