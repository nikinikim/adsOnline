package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.*;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.Image;
import com.example.adsonline.entity.User;
import com.example.adsonline.exception.UserForbiddenException;
import com.example.adsonline.repository.AdsRepository;
import com.example.adsonline.services.AdsMapperService;
import com.example.adsonline.services.AdsService;
import com.example.adsonline.services.ImageService;
import com.example.adsonline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdsMapperService adsMapper;


    /**
     * Метод ищет и возвращает список всех объявлений
     *
     * @return ResponsesWrapperAdsDTO
     */
    @Override
    public ResponsesWrapperAdsDTO getAllAdsDTO() {
        List<AdsDTO> adsAll = adsMapper.createAdListToAdDTOList(adsRepository.findAll());
        return new ResponsesWrapperAdsDTO();
    }

    /**
     * Метод создает объявление
     *
     * @param adsDTO
     * @param image
     * @return AdsDto
     */
    @Override
    public AdsDTO createAds(CreateAdsDTO adsDTO, MultipartFile image) throws IOException {
        Ads newAd = adsMapper.createdAdsDTOToAd(adsDTO);
        newAd.setUser(userService.getUserById(newAd.getIdAds()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден")));
        byte[] newImage = imageService.updateAdImages(newAd.getIdAds(), image);
        newAd.setImage(new Image());
        adsRepository.save(newAd);
        return adsMapper.createAdToAdDTO(newAd);
    }

    /**
     * Метод ищет и возвращает объявление по id
     *
     * @param id
     * @return FullAdsDto
     */
    @Override
    public FullAdsDTO getFullAdsDTO(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return adsMapper.createAdToFullAdsDTO(ads);
    }

    /**
     * Метод удаляет объявление по id
     *
     * @param id
     */
    @Override
    public boolean deleteAdsDTO(Integer id) {
        if (checkAccess(id)) {
            adsRepository.deleteById(id);
            return true;
        }
        throw new UserForbiddenException();
    }


    /**
     * Метод редактирует объявление по id
     *
     * @param id
     * @param createAdsDTO
     * @return AdsDto
     */
    @Override
    public AdsDTO updateAdsDTO(Integer id, CreateAdsDTO createAdsDTO) {
        Ads ads = adsRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        ;
        if (checkAccess(id)) {
            ads.setDescription(createAdsDTO.getDescription());
            ads.setPrice(createAdsDTO.getPrice());
            ads.setTitle(createAdsDTO.getTitle());
            return adsMapper.createAdToAdDTO(adsRepository.save(ads));
        }
        throw new UserForbiddenException();
    }

    /**
     * Метод ищет и возвращает список всех объявлений авторизированного пользователя
     *
     * @return ResponseWrapperAdsDto
     */
    @Override
    public ResponsesWrapperAdsDTO getAllUserAdsDTO() {
        User user = userService.getUserById(getAllUserAdsDTO().getCount()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        ;
        List<Ads> allAds = adsRepository.findAll();
        List<Ads> userAds = allAds.stream().filter(x -> x.user.getLastName().equals(user)).collect(Collectors.toList());
        return new ResponsesWrapperAdsDTO();
    }

    /**
     * Метод обновляет изображение к объявлению по id
     *
     * @param id
     * @param image
     * @return
     */
    @Override
    public AdsDTO updateImageAdDto(Integer id, MultipartFile image) throws IOException {
        Ads ad = adsRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        byte[] updatedImage = imageService.updateAdImages(ad.getIdAds(), image);
        ad.setImage(new Image());
        adsRepository.save(ad);
        return adsMapper.createAdToAdDTO(ad);
    }


    /**
     * Метод проверяет наличие доступа к объявлению по id
     *
     * @param id
     */
    @Override
    public boolean checkAccess(Integer id) {
        RoleDTO role = RoleDTO.ADMIN;
        Ads ads = adsRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        ;
        Optional<User> user = userService.getUserById(ads.getIdAds());
        User notOptionalUser = user.get();
        String currentPrincipalName = notOptionalUser.getFirstName();
        return ads.getUser().getFirstName().equals(currentPrincipalName);

    }

    @Override
    public List<CommentDTO> getCommentsForAd(int adId) {
        List<CommentDTO> comments = new ArrayList<>();
        CommentDTO comment1 = new CommentDTO();
//        comment1.setAuthor(1);
//        comment1.setCreatedAt("2023-07-27");
//        comment1.setPk(1);
//        comment1.setText("Test comment 1");
        comments.add(comment1);

        CommentDTO comment2 = new CommentDTO();
//        comment2.setAuthor(2);
//        comment2.setCreatedAt("2023-07-28");
//        comment2.setPk(2);
//        comment2.setText("Test comment 2");
        comments.add(comment2);

        return comments;
    }


}
