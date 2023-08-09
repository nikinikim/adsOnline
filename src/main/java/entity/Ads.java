package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAds;
    private String description;
    private int price;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne
    private Image image;

}