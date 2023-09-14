package com.example.adsonline.DTOs;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer author;
    private String createdAt;
    private Integer pk;
    private String text;
}
