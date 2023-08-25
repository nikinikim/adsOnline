package com.example.adsonline.mappers;

import com.example.adsonline.DTOs.AdsDTO;
import com.example.adsonline.entity.Ads;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public abstract class AdsMapper implements GeneralMapper<Ads, AdsDTO> {
}
