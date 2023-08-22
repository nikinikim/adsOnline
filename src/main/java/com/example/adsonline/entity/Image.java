package com.example.adsonline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {
    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Объявление
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    private Ads ad;
    /**
     * Ссылка на картинку
     */
    @Column(name = "image_ref", length = Integer.MAX_VALUE)
    private String imageRef;

}