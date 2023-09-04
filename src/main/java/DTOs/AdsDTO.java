package DTOs;

import lombok.Data;

import java.util.List;


@Data
public class AdsDTO {
    private int author;
    private List<String> image;
    private int pk;
    private int price;
    private String title;
}