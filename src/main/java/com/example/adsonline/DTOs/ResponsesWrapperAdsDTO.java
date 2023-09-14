package com.example.adsonline.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class ResponsesWrapperAdsDTO {

    private int count;
    private List<AdsDTO> results;
}
