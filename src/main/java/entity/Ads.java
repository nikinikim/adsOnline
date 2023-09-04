package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
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
    @Column(name = "id", nullable = false)
    private Integer id;
    /**
     * Заголовок объявления
     */
    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;
    /**
     * Стоимость
     */
    @Column(name = "price", precision = 15, scale = 2)
    private Integer price;
    /**
     * Описание
     */
    @Column(name = "description")
    private String description;
    /**
     * Картинки
     */
    @OneToMany(mappedBy = "ads",orphanRemoval = true, cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private Set<Image> images = new LinkedHashSet<>();
    /**
     * Автор
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * Комментарии
     */
    @OneToMany(mappedBy = "ads",orphanRemoval = true, cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private List<Comment> comments;
}