package com.example.adsonline.repository;

import com.example.adsonline.entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {


    List<Ads> findByPrice(int price);

    List<Ads> findByTitle(String tittle);

    List<Ads> findAllByUser_UserName(String userName);
}