package com.example.adsonline.DTOs;

import lombok.Data;

@Data
public class CreateAdsDTO {
    private String description;
    private int price;
    private String title;
}
