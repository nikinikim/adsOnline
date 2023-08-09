package mapper;

import DTOs.AdsDTO;
import DTOs.CreateAdsDTO;
import DTOs.FullAdsDTO;
import entity.Ads;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdsMapper {

    Ads toAd(CreateAdsDTO createAdsDTO);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "image", expression="java(getImage(ad))")
    AdsDTO toAdsDTO(Ads ads);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.username")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "image", expression="java(getImage(ad))")
    FullAdsDTO toFullAdsDTO(Ads ads);

    default String getImage(Ads ads) {
        if (ads.getImage() == null) {
            return null;
        }
        return "/ads/image/" + ads.getId() + "/from-db";
    }

    List<AdsDTO> adListToAdsDtoList(List<Ads> adList);


}