package services.impl;

import DTOs.AdsDTO;
import DTOs.Comment;
import DTOs.CreateAds;
import DTOs.FullAds;
import entity.Ads;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.AdService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    //Тестовые данные (потом необходимо удалить)
    private List<AdsDTO> adsList;
    private List<Comment> comments;

    @Override
    public List<AdsDTO> getAllAds() {
        return new ArrayList<>();
    }

    @Override
    public AdsDTO getAdById(int id) {
        return new AdsDTO();
    }

    @Override
    public AdsDTO createAd(AdsDTO ad) {
        return new AdsDTO();
    }

    @Override
    public AdsDTO updateAd(int id, CreateAds ad) {
        return new AdsDTO();
    }

    @Override
    public void removeAd(int id) {

    }

    @Override
    public AdsDTO addAd(MultipartFile image, CreateAds createAds) {
        return new AdsDTO();
    }
    @Override
    public FullAds getFullAdById(int id) {
        return new FullAds();
    }

    @Override
    public List<Comment> getCommentsForAd(int adId) {
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setAuthor(1);
        comment1.setCreatedAt("2023-07-27");
        comment1.setPk(1);
        comment1.setText("Test comment 1");
        comments.add(comment1);

        Comment comment2 = new Comment();
        comment2.setAuthor(2);
        comment2.setCreatedAt("2023-07-28");
        comment2.setPk(2);
        comment2.setText("Test comment 2");
        comments.add(comment2);

        return comments;
    }

    @Override
    public AdsDTO addToDTO(Ads ads) {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setAuthor(ads.getAuthor());
        adsDTO.setImage(ads.getImage());
        adsDTO.setPk(ads.getPk());
        adsDTO.setPrice(ads.getPrice());
        adsDTO.setTitle(ads.getTitle());

        return adsDTO;
    }

    @Override
    public Ads addToEntity(AdsDTO adsDTO) {
        Ads ads = new Ads();
        ads.setAuthor(adsDTO.getAuthor());
        ads.setImage(adsDTO.getImage());
        ads.setPk(adsDTO.getPk());
        ads.setPrice(adsDTO.getPrice());
        ads.setTitle(adsDTO.getTitle());

        return ads;
    }


}
