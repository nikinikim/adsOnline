package mappers;

import DTOs.AdsDTO;
import entity.Ads;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public abstract class AdsMapper implements GeneralMapper<Ads, AdsDTO> {
}
