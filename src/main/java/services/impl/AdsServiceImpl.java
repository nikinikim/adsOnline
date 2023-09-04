package services.impl;

import DTOs.AdsDTO;
import DTOs.CreateAdsDTO;
import DTOs.FullAdsDTO;
import DTOs.ResponsesWrapperAdsDTO;
import entity.Ads;
import entity.User;
import mappers.AdsMapper;
import repository.AdsRepository;
import services.UserService;
import com.example.adsonline.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.AdsService;

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
        User user = userService.findUserByLogin(principal.getName());
        Ads ads = adsMapper.fromCreateAdsDTO(createAdsDTO);
        ads.setUser(user);
        return adsMapper.toDto(adsRepository.saveAndFlush(ads));
    }

    @Override
    public FullAdsDTO getFullAdsDTO(Integer id) {
        return null;
    }

    @Override
    public void deleteAdsDTO(Integer id) {

    }

    @Override
    public AdsDTO updateAdsDTO(Integer id, CreateAdsDTO createAdsDTO) {
        return null;
    }
}
