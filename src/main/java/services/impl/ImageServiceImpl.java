package services.impl;

<<<<<<< HEAD:src/main/java/com/example/adsonline/services/impl/ImageServiceImpl.java
import services.ImageService;
=======
>>>>>>> Kristina_feature_23.08:src/main/java/services/impl/ImageServiceImpl.java
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
