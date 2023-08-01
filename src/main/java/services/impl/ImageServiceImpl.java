package services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.ImageService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public List<String> updateAdImages(int adId, List<MultipartFile> images) {
        return new ArrayList<>();
    }
}
