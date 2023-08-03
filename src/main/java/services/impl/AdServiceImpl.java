package services.impl;

import DTOs.AdsDTO;
import DTOs.CommentDTO;
import DTOs.CreateAdsDTO;
import DTOs.FullAdsDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.AdService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    //Тестовые данные (потом необходимо удалить)
    private List<AdsDTO> adsList;
    private List<CommentDTO> comments;

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
    public AdsDTO updateAd(int id, CreateAdsDTO ad) {
        return new AdsDTO();
    }

    @Override
    public void removeAd(int id) {

    }

    @Override
    public AdsDTO addAd(MultipartFile image, CreateAdsDTO createAds) {
        return new AdsDTO();
    }
    @Override
    public FullAdsDTO getFullAdById(int id) {
        return new FullAdsDTO();
    }

    @Override
    public List<CommentDTO> getCommentsForAd(int adId) {
        List<CommentDTO> comments = new ArrayList<>();
        CommentDTO comment1 = new CommentDTO();
        comment1.setAuthor(1);
        comment1.setCreatedAt("2023-07-27");
        comment1.setPk(1);
        comment1.setText("Test comment 1");
        comments.add(comment1);

        CommentDTO comment2 = new CommentDTO();
        comment2.setAuthor(2);
        comment2.setCreatedAt("2023-07-28");
        comment2.setPk(2);
        comment2.setText("Test comment 2");
        comments.add(comment2);

        return comments;
    }


}
