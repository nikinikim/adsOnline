package com.example.adsonline.DTOs;

import java.util.List;

public class ResponseWrapperCommentDTO {
    private int count;
    private List<CommentDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CommentDTO> getResults() {
        return results;
    }

    public void setResults(List<CommentDTO> results) {
        this.results = results;
    }
}