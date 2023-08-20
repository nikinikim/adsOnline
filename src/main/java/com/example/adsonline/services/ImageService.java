package com.example.adsonline.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    byte[] updateAdImages(int adId, MultipartFile image) throws IOException;
}
