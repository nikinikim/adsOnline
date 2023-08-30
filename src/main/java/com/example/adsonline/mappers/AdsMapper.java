package com.example.adsonline.mappers;

import com.example.adsonline.DTOs.AdsDTO;
import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.entity.Ads;
import com.example.adsonline.entity.Comment;
import com.example.adsonline.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring"
)
public abstract class AdsMapper implements GeneralMapper<Ads, AdsDTO> {
    @Override
    @Mappings({
            @Mapping(source = "adsDTO.author", target = "author",
                    qualifiedByName = "authorMapper")
    })
    public abstract Ads fromDto(AdsDTO adsDTO);

    @Override
    @Mapping(source = "author.id", target = "author")
    public abstract AdsDTO toDto(Ads ads);

    @Named("userMapper")
    User userMapper(Integer userId) {
        if (userId!= null) {
            User user = new User();
            user.setId(Long.valueOf(userId));
            return user;
        }
        return null;
    }
}
