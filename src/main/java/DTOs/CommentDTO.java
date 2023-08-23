package DTOs;

import lombok.Data;

@Data
public class CommentDTO {
    private int author;
    private String createdAt;
    private int pk;
    private String text;
}
