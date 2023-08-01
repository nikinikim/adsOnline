package services.impl;

import DTOs.Ads;
import DTOs.Comment;
import DTOs.CreateAds;
import DTOs.FullAds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.AdService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    //Тестовые данные (потом необходимо удалить)
    private List<Ads> adsList;
    private List<Comment> comments;

    @Override
    public List<Ads> getAllAds() {
        return new ArrayList<>();
    }

    @Override
    public Ads getAdById(int id) {
        return new Ads();
    }

    @Override
    public Ads createAd(Ads ad) {
        return new Ads();
    }

    @Override
    public Ads updateAd(int id, CreateAds ad) {
        return new Ads();
    }

    @Override
    public void removeAd(int id) {

    }

    @Override
    public Ads addAd(MultipartFile image, CreateAds createAds) {
        return new Ads();
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


}
