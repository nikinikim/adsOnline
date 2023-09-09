package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.AdsDTO;
import com.example.adsonline.DTOs.CreateAdsDTO;
import com.example.adsonline.DTOs.FullAdsDTO;
import com.example.adsonline.DTOs.ResponsesWrapperAdsDTO;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.User;
import com.example.adsonline.exception.AdsNotFoundException;
import com.example.adsonline.mappers.AdsMapper;
import com.example.adsonline.repository.AdsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AdsServiceImplTest {

    @InjectMocks
    private AdsServiceImpl adsService;
    @Mock
    private AdsRepository adsRepository;
    @Mock
    private AdsMapper adsMapper;
    private final String FIRST_NAME = "testFirstName";
    private final String DIF_FIRST_NAME = "differentFirstName@mail.com";
    private final String LAST_NAME = "testLastName";
    private final String DIF_LAST_NAME = "differentTestLastName";
    private final User testUser = new User();
    private final User diffUser = new User();
    private final Ads testAd = new Ads();
    private final CreateAdsDTO createAdsDto = new CreateAdsDTO();

    @BeforeEach
    public void setUp() {
        testUser.setFirstName(FIRST_NAME);
        testUser.setLastName(LAST_NAME);

        diffUser.setFirstName(DIF_FIRST_NAME);
        diffUser.setLastName(DIF_LAST_NAME);


        testAd.getIdAds();
        testAd.setDescription("test description");
        testAd.setTitle("test title");
        testAd.setPrice(50);

        createAdsDto.setDescription("New Description");
        createAdsDto.setTitle("New Title");
        createAdsDto.setPrice(500);
    }


    @Test
    @DisplayName("Проверка доступа к объявлению, когда объявление не найдено")
    public void checkAccessForNonExistentAdTest() {
        when(adsRepository.findById(testAd.getIdAds())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class, () -> adsService.getFullAdsDTO(testAd.getIdAds()));

        verify(adsRepository, times(1)).findById(testAd.getIdAds());
    }

    @Test
    @DisplayName("Проверка возвращения списка объявлений")
    public void getAllAdsDtoTest() {
        List<Ads> adList = Collections.singletonList(new Ads());
        List<AdsDTO> adsDtoList = Collections.singletonList(new AdsDTO());

        when(adsRepository.findAll()).thenReturn(adList);
        when(adsMapper.toResponsesWrapperAdsDTO(adList)).thenReturn((ResponsesWrapperAdsDTO) adsDtoList);

        ResponsesWrapperAdsDTO result = adsService.getAllAds();

        assertNotNull(result);
        assertEquals(adsDtoList, result.getResults());

        verify(adsRepository, times(1)).findAll();
        verify(adsMapper, times(1)).toResponsesWrapperAdsDTO(adList);
    }

    @Test
    @DisplayName("Проверка получения всех данных из объявления")
    public void getFullAdDtoTest() {
        FullAdsDTO fullAdsDto = new FullAdsDTO();

        when(adsRepository.findById(testAd.getIdAds())).thenReturn(Optional.of(testAd));
        when(adsMapper.toFullAdsDTO(testAd)).thenReturn(fullAdsDto);

        FullAdsDTO result = adsService.getFullAdsDTO(testAd.getIdAds());

        assertEquals(fullAdsDto, result);

        verify(adsRepository, times(1)).findById(testAd.getIdAds());
        verify(adsMapper, times(1)).toResponsesWrapperAdsDTO((List<Ads>) testAd);
    }


    @Test
    @DisplayName("Проверка невозможности удаления объявления, когда оно не существует")
    public void removeAdDtoWhenAdDoesntExistTest() {
        when(adsRepository.findById(testAd.getIdAds())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class, () -> adsService.deleteAdsDTO(testAd.getIdAds()));

        verify(adsRepository, times(1)).findById(testAd.getIdAds());
    }


    @Test
    @DisplayName("Проверка невозможности редактирования объявления, когда оно не существует")
    public void updateAdDtoWhenAdDoesntExistTest() {
        when(adsRepository.findById(testAd.getIdAds())).thenReturn(Optional.empty());

        assertThrows(AdsNotFoundException.class, () -> adsService.updateAdsDTO(testAd.getIdAds(), createAdsDto));

        verify(adsRepository, times(1)).findById(testAd.getIdAds());
    }


}

