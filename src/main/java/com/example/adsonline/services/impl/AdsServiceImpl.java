package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.*;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.Users;
import com.example.adsonline.mappers.AdsMapper;
import com.example.adsonline.repository.AdsRepository;
import com.example.adsonline.services.AdsService;
import com.example.adsonline.services.UserService;
import com.example.adsonline.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;


@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final UserService userService;


    @Override
    @Transactional(readOnly = true)
    public ResponsesWrapperAdsDTO getAllAds() {
        return adsMapper.toResponsesWrapperAdsDTO(adsRepository.findAll());

    }

    @Override
    @Transactional(readOnly = true)
    public ResponsesWrapperAdsDTO getAllAdsMe(Principal principal) {
        return adsMapper.toResponsesWrapperAdsDTO(adsRepository.findAllByUser_UserName(principal.getName()));
    }

    @Override
    @Transactional
    public AdsDTO createAds(String adsDTO, Principal principal) {
        CreateAdsDTO createAdsDTO = CommonUtils.objectFromString(adsDTO, CreateAdsDTO.class);
        Users user = userService.findUserByLogin(principal.getName());
        Ads ads = adsMapper.fromCreateAdsDTO(createAdsDTO);
        ads.setUser(user);
        return adsMapper.toDto(adsRepository.saveAndFlush(ads));
    }

    @Override
    @Transactional(readOnly = true)
    public FullAdsDTO getFullAdsDTO(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow();
        return adsMapper.toFullAdsDTO(ads);
    }

    @Override
    @Transactional
    public void deleteAdsDTO(Integer id) {
        adsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AdsDTO updateAdsDTO(Integer id, CreateAdsDTO createAdsDTO) {
        Ads ads = adsRepository.findById(id).orElseThrow();
        ads.setDescription(createAdsDTO.getDescription());
        ads.setTitle(createAdsDTO.getTitle());
        ads.setPrice(createAdsDTO.getPrice());
        return adsMapper.toDto(adsRepository.saveAndFlush(ads));
    }
}