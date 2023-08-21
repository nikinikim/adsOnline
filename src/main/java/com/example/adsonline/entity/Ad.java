package com.example.adsonline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ad")
public class Ad {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
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
    @OneToMany(mappedBy = "ad")
    private Set<Image> images = new LinkedHashSet<>();
    /**
     * Автор
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}