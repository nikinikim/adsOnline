package com.example.adsonline.services;

import com.example.adsonline.DTOs.*;
import com.example.adsonline.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс сервисного класса AdsServiceImpl, содержащий набор CRUD операций над объектом Ads
 *
 */
public interface AdsService {

    /**
     * Метод ищет и возвращает список всех объявлений
     *
     * @return ResponseWrapperAdsDTO
     */
    ResponsesWrapperAdsDTO getAllAdsDTO();

    /**
     * Метод создает объявление
     *
     * @param adsDTO
     * @param file
     * @return AdsDto
     */
    AdsDTO createAds(CreateAdsDTO adsDTO, MultipartFile file) throws IOException;

    /**
     * Метод ищет и возвращает объявление по id
     *
     * @param id
     * @return FullAdsDTO
     */
    AdsDTO getFullAdsDTO(Integer id);

    /**
     * Метод удаляет объявление по id
     *
     * @param id
     */
    void deleteAdsDTO(Integer id);

    /**
     * Метод редактирует объявление по id
     *
     * @param id
     * @param createAdsDTO
     * @return AdsDTO
     */
    AdsDTO updateAdsDTO(Integer id, CreateAdsDTO createAdsDTO);

    /**
     * Метод ищет и возвращает список всех объявлений авторизированного пользователя
     *
     * @return ResponsesWrapperAdsDTO
     */
    ResponsesWrapperAdsDTO getAllUserAdsDTO();

    /**
     * Метод обновляет изображение к объявлению по id
     *
     * @param id
     * @param image
     * @return
     */
    AdsDTO updateImageAdDto(Integer id, MultipartFile image) throws IOException;

    List<CommentDTO> getCommentsForAd(int adId);
}
