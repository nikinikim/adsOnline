package DTOs;

import com.example.adsonline.DTOs.CommentDTO;

import java.util.List;

public class ResponseWrapperCommentDTO {
    private int count;
    private List<com.example.adsonline.DTOs.CommentDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<com.example.adsonline.DTOs.CommentDTO> getResults() {
        return results;
    }

    public void setResults(List<CommentDTO> results) {
        this.results = results;
    }
}
