package services.impl;

import DTOs.AdsDTO;
import DTOs.CreateAdsDTO;
import DTOs.FullAdsDTO;
import com.example.adsonline.entity.Ads;
import services.AdsMapperService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AdsMapperServiceImpl implements AdsMapperService {


    @Override
    public AdsDTO createAdToAdDTO(Ads ads) {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setPrice(ads.getPrice());
        adsDTO.setImage(Collections.singletonList("/ads/" + ads.getImage().getId() + "/image"));
        adsDTO.setTitle(ads.getTitle());
        return adsDTO;
    }

    @Override
    public Ads createAdsDTOToAd(AdsDTO adsDTO) {
        Ads ad = new Ads();
        ad.setPrice(adsDTO.getPrice());
        ad.setTitle(adsDTO.getTitle());
        return ad;
    }

    @Override
    public FullAdsDTO createAdToFullAdsDTO(Ads ads) {
        FullAdsDTO fullAdsDTO = new FullAdsDTO();
        fullAdsDTO.setPk(ads.getIdAds());
        fullAdsDTO.setAuthorLastName(ads.user.getLastName());
        fullAdsDTO.setEmail(ads.user.getEmail());
        fullAdsDTO.setPhone(ads.user.getPhone());
        fullAdsDTO.setTitle(ads.getTitle());
        fullAdsDTO.setDescription(ads.getDescription());
        fullAdsDTO.setImage(Collections.singletonList("/ads/" + ads.getImage().getId() + "/image"));
        fullAdsDTO.setPrice(ads.getPrice());
        return fullAdsDTO;
    }

    @Override
    public Ads createdAdsDTOToAd(CreateAdsDTO createAdsDTO) {
        Ads ads= new Ads();
        ads.setTitle(createAdsDTO.getTitle());
        ads.setDescription(createAdsDTO.getDescription());
        ads.setPrice(createAdsDTO.getPrice());
        return ads;
    }
    @Override
    public List<AdsDTO> createAdListToAdDTOList(List<Ads> adsList) {
        List<AdsDTO> dtoList = new ArrayList<AdsDTO>(adsList.size());
        for (Ads ads : adsList) {
            dtoList.add(createAdToAdDTO(ads));
        }
        return dtoList;
    }

}