package com.example.adsonline.services;

import com.example.adsonline.DTOs.AdsDTO;
import com.example.adsonline.DTOs.CreateAdsDTO;
import com.example.adsonline.DTOs.FullAdsDTO;
import com.example.adsonline.entity.Ads;

import java.util.List;

public interface AdsMapperService {
    /**
     * Метод, преобразующий объект класса Ads в объект класса AdsDTO.
     *
     * @param ads
     * @return AdsDTO
     */
    AdsDTO createAdToAdDTO(Ads ads);

    /**
     * Метод, преобразующий объект класса AdsDTO в объект класса Ad.
     *
     * @param adsDTO
     * @return Ads
     */
    Ads createAdsDTOToAd(AdsDTO adsDTO);

    /**
     * Метод, преобразующий объект класса Ads в объект класса FullAdsDTO.
     *
     * @param ads
     * @return FullAdsDto
     */
    FullAdsDTO createAdToFullAdsDTO(Ads ads);

    /**
     * Метод, преобразующий объект класса CreateAdsDTO в объект класса Ads.
     *
     * @param createAdsDTO
     * @return Ads
     */
    Ads createdAdsDTOToAd(CreateAdsDTO createAdsDTO);

    /**
     * Метод, преобразующий объект класса List<Ads> в объект класса List<AdsDTO>.
     *
     * @param adsList
     * @return List<AdsDTO>
     */
    List<AdsDTO> createAdListToAdDTOList(List<Ads> adsList);

}


