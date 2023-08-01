package services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<String> updateAdImages(int adId, List<MultipartFile> images);
}
