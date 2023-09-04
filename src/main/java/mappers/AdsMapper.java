package mappers;

import com.amazonaws.util.CollectionUtils;
import DTOs.AdsDTO;
import DTOs.CreateAdsDTO;
import DTOs.FullAdsDTO;
import DTOs.ResponsesWrapperAdsDTO;
import entity.Ads;
import entity.Image;
import mappers.GeneralMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring"
)
public abstract class AdsMapper implements GeneralMapper<Ads, AdsDTO> {

    public ResponsesWrapperAdsDTO toResponsesWrapperAdsDTO(List<Ads> ads) {
        ResponsesWrapperAdsDTO responsesWrapperAdsDTO = new ResponsesWrapperAdsDTO();
        responsesWrapperAdsDTO.setCount(ads.size());
        responsesWrapperAdsDTO.setResults(ads.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList()));
        return responsesWrapperAdsDTO;
    }

    public Ads fromCreateAdsDTO(CreateAdsDTO createAdsDTO) {
        Ads ads = new Ads();
        ads.setTitle(createAdsDTO.getTitle());
        ads.setPrice(createAdsDTO.getPrice());
        ads.setDescription(createAdsDTO.getDescription());
        return ads;
    }

    public FullAdsDTO toFullAdsDTO(Ads ads) {
        FullAdsDTO fullAdsDTO = new FullAdsDTO();
        fullAdsDTO.setAuthorFirstName(ads.getUser().getFirstName());
        fullAdsDTO.setAuthorLastName(ads.getUser().getLastName());
        fullAdsDTO.setPk(ads.getId());
        fullAdsDTO.setEmail(ads.getUser().getUserName());
        fullAdsDTO.setPhone(ads.getUser().getPhone());
        fullAdsDTO.setDescription(ads.getDescription());
        fullAdsDTO.setPrice(ads.getPrice());
        fullAdsDTO.setTitle(ads.getTitle());
        fullAdsDTO.setImage(ads.getImages().stream().map(Image::getImageRef).collect(Collectors.toList()));
        return fullAdsDTO;
    }

    @Override
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "images", target = "image",
            qualifiedByName = "imagesMapper")
    @Mapping(source = "user.id", target = "author")
    public abstract AdsDTO toDto(Ads ads);

    @Named("imagesMapper")
    List<String> imagesMapper(Set<Image> images) {
        if (!CollectionUtils.isNullOrEmpty(images)) {
            return images.stream().filter(Objects::nonNull).map(Image::getImageRef).collect(Collectors.toList());
        }
        return null;
    }
}

