package entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ads")
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int author;
    private List<String> image;
    @Column(nullable = false)
    private int pk;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, length = 1000)
    private String title;

    public Ads() {
    }

    public Ads(Long id, int author, List<String> image, int pk, int price, String title) {
        this.id = id;
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }
    public void setAuthor(int author) {
        this.author = author;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
