package services.impl;

import DTOs.*;
import entity.Ads;
import entity.Image;
import mapper.AdsMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.AdsRepository;
import repository.ImageRepository;
import repository.UserRepository;
import services.AdsService;
import services.CommentService;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final AdsMapper adsMapper;
    private final CommentService commentService;

    public AdsServiceImpl(AdsRepository adsRepository, ImageRepository imageRepository, UserRepository userRepository, UserService userService, AdsMapper adsMapper, CommentService commentService) {
        this.adsRepository = adsRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.adsMapper = adsMapper;
        this.commentService = commentService;
    }


    @Override
    public ResponsesWrapperAdsDTO getAllAds() {
        ResponsesWrapperAdsDTO responseWrapperAdsDTO = new ResponsesWrapperAdsDTO();
        List<Ads> adList = adsRepository.findAll();
        responseWrapperAdsDTO.setResults(adsMapper.adListToAdsDtoList(adList));
        responseWrapperAdsDTO.setCount(adList.size());
        return responseWrapperAdsDTO;
    }

    @Override
    public AdsDTO getAdById(int id) {
        return new AdsDTO();
    }

    @Override
    public AdsDTO createAd(AdsDTO ad) {

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
        Ads ads = adsMapper.toAd(createAds);
        Image image = new Image();

        image.setMediaType(file.getContentType());
        image.setData(file.getBytes());
        imageRepository.save(image);
        ads.setImage(image);
        ads.setUser(userRepository.findByUsername(userService.getCurrentUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found")));
        adsRepository.save(ads);
        return adsMapper.toAdsDTO(ads);
    }
    @Override
    public FullAdsDTO getFullAdById(Long id) {
        return adsRepository.findById(id).map(adsMapper::toFullAdsDTO).orElse(null);
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
