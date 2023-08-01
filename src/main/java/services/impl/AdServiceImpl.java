package services.impl;

import DTOs.Ads;
import org.springframework.stereotype.Service;
import services.AdService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public List<Ads> getAllAds() {
        return new ArrayList<>();
    }

    @Override
    public Ads getAdById(int id) {
        return new Ads();
    }

    @Override
    public Ads createAd(Ads ad) {
        return new Ads();
    }

    @Override
    public Ads updateAd(int id, Ads ad) {
        return new Ads();
    }

    @Override
    public void deleteAd(int id) {

    }

}
