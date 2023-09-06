package com.example.adsonline.repository;

import com.example.adsonline.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findImageByAds_Id(Integer id);
    boolean existsImageByAds_Id(Integer id);
}
