package com.example.adsonline.DTOs;

import lombok.Data;
import java.util.List;

@Data
public class ResponseWrapperCommentDTO {
    private int count;
    private List<CommentDTO> results;
}
