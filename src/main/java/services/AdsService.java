package services;

import DTOs.*;
import com.example.adsonline.DTOs.*;
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
     * @param image
     * @return AdsDto
     */
    AdsDTO createAds(CreateAdsDTO adsDTO, MultipartFile image) throws IOException;

    /**
     * Метод ищет и возвращает объявление по id
     *
     * @param id
     * @return FullAdsDTO
     */
    FullAdsDTO getFullAdsDTO(Integer id);

    /**
     * Метод удаляет объявление по id
     *
     * @param id
     */
    boolean deleteAdsDTO(Integer id);

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

    /**
     * Метод проверяет наличие доступа к объявлению по id
     *
     * @param id
     */
    boolean checkAccess(Integer id);

    List<CommentDTO> getCommentsForAd(int adId);
}
