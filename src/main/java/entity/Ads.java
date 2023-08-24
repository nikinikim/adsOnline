package entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ads")
public class Ads {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAds", nullable = false)
    private Long idAds;
    /**
     * Заголовок объявления
     */
    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;
    /**
     * Стоимость
     */
    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;
    /**
     * Описание
     */
    @Column(name = "description")
    private String description;
    /**
     * Картинки
     */
    @OneToMany(mappedBy = "ads")
    private Set<Image> images = new LinkedHashSet<>();
    /**
     * Автор
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}