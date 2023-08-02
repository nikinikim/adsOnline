package services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.ImageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] updateAdImages(int adId, MultipartFile image) throws IOException {

        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Изображение отсутствует или пустое.");
        }

        byte[] imageData = image.getBytes();

        return imageData;
    }
}
