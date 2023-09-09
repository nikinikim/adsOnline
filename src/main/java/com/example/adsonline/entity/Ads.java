package com.example.adsonline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    @Column(name = "idAds", nullable = false)
    private Integer idAds;
    /**
     * Заголовок объявления
     */
    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;
    /**
     * Стоимость
     */
    @Column(name = "price", precision = 15, scale = 2)
    private int price;
    /**
     * Описание
     */
    @Column(name = "description")
    private String description;
    /**
     * Картинки
     */
    @OneToMany(mappedBy = "ads")
    private List<Image> images;
    /**
     * Автор
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}