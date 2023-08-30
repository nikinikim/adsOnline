package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.*;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.Image;
import com.example.adsonline.entity.User;
import com.example.adsonline.exception.UserForbiddenException;
import com.example.adsonline.mappers.AdsMapper;
import com.example.adsonline.repository.AdsRepository;
import com.example.adsonline.repository.ImageRepository;
import com.example.adsonline.repository.UserRepository;
import com.example.adsonline.services.AdsService;
import com.example.adsonline.services.CommentService;
import com.example.adsonline.services.ImageService;
import com.example.adsonline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdsMapper adsMapper;
    private final ImageRepository imageRepository;
    private final CommentService commentService;
    private final UserRepository userRepository;





    /**
     * Метод ищет и возвращает список всех объявлений
     *
     * @return ResponsesWrapperAdsDTO
     */
    @Override
    public ResponsesWrapperAdsDTO getAllAdsDTO() {
        List<Ads> ads = adsRepository.findAll();
        ResponsesWrapperAdsDTO wrapperAds = new ResponsesWrapperAdsDTO();
        wrapperAds.setCount(ads.size());
        wrapperAds.setResults(
                ads.stream()
                        .map(adsMapper::toDto)
                        .collect(Collectors.toList())
        );
        //return AdsMapper.INSTANCE.adsModelToAds(a);
        return wrapperAds;
    }




    /**
     * Метод создает объявление
     *
     * @param adsDTO
     * @param file
     * @return AdsDto
     */
    @Override
    public AdsDTO createAds(CreateAdsDTO adsDTO, MultipartFile file) throws IOException {

        Ads ads = new Ads();
        Image image = new Image();
        image.getImageRef();
        imageRepository.save(image);
        ads.getImages();
        ads.setUser(userRepository.getReferenceById(userService.getUserById(ads.getIdAds())));
        adsRepository.save(ads);
        return adsMapper.toDto(ads);
    }


    /**
     * Метод ищет и возвращает объявление по id
     *
     * @param id
     * @return FullAdsDto
     */
    @Override
    public AdsDTO getFullAdsDTO(Integer id) {
        Ads ads = adsRepository.findById(id).orElse(null);
        return adsMapper.toDto(ads);
    }

    /**
     * Метод удаляет объявление по id
     *
     * @param id
     */
    @Override
    public void deleteAdsDTO(Integer id) {

        imageRepository.delete((Image) adsRepository.findById(id).map(Ads::getImages).orElseThrow());
        commentService.deleteComment(id);
        adsRepository.deleteById(id);
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
        Ads ad = adsRepository.findById(id).orElseThrow();
        ad.setTitle(createAdsDTO.getTitle());
        ad.setDescription(createAdsDTO.getDescription());
        ad.setPrice(createAdsDTO.getPrice());
        adsRepository.save(ad);

        return adsMapper.toDto(ad);
    }


    /**
     * Метод ищет и возвращает список всех объявлений авторизированного пользователя
     *
     * @return ResponseWrapperAdsDto
     */
    @Override
    public ResponsesWrapperAdsDTO getAllUserAdsDTO() {
        List<AdsDTO> adsAll = Collections.singletonList(adsMapper.toDto((Ads) adsRepository.findAll()));
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
    public AdsDTO updateImageAdDto(Integer id, MultipartFile image) throws IOException{
        Ads ad = adsRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        byte[] updatedImage = imageService.updateAdImages(Math.toIntExact(ad.getIdAds()), image);
        ad.setImages((List<Image>) new Image());
        adsRepository.save(ad);
        return adsMapper.toDto(ad);
    }


    private void setImage(MultipartFile image, Ads ads) throws IOException {
        String oldName = ads.getImages().toString();
        String sourceName = image.getOriginalFilename();
        String fileName = ads.getIdAds() + sourceName.substring(sourceName.lastIndexOf("."));
        Path path = Path.of(String.valueOf(image)).resolve(fileName);
        try {
            if (oldName != null) {
                Files.deleteIfExists(Path.of(oldName));
            }
        } catch (IOException e) {
            throw new IOException("Ad's image was not saved", e);
        }
        ads.setImages((List<Image>) image);
        adsRepository.save(ads);
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
