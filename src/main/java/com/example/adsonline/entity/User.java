package com.example.adsonline.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Имя пользователя
     */
    @Column(name = "first_name", length = Integer.MAX_VALUE)
    private String firstName;
    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name", length = Integer.MAX_VALUE)
    private String lastName;
    /**
     * Эл почта пользователя
     */
    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;
    /**
     * Телефон пользователя
     */
    @Column(name = "phone", length = Integer.MAX_VALUE)
    private String phone;
    /**
     * Дата регистрации пользователя
     */
    @Column(name = "reg_date", length = Integer.MAX_VALUE)
    private String regDate;
    /**
     * Город пользователя
     */
    @Column(name = "city", length = Integer.MAX_VALUE)
    private String city;
    /**
     * Ссылка на фото пользователя
     */
    @Column(name = "image_ref", length = Integer.MAX_VALUE)
    private String imageRef;
    /**
     * Объявления пользователя
     */
    @OneToMany(mappedBy = "user")
    private Set<Ads> ads = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "new_password_id")
    private NewPassword newPassword;
    @ManyToOne
    @JoinColumn(name = "register_req_id")
    private RegisterReq registerReq;

    public User(String email, String firstName, String lastName, String phone, String regDate, String city, String imageRef,
                NewPassword newPassword, RegisterReq registerReq) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.regDate = regDate;
        this.city = city;
        this.imageRef = imageRef;
        this.newPassword = newPassword;
        this.registerReq = registerReq;
    }

    public User() {
    }

}