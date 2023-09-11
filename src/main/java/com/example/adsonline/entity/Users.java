package com.example.adsonline.entity;

import com.example.adsonline.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    /**
     * Логин
     */
    @Column(name = "user_name", nullable = false, unique = true, length = Integer.MAX_VALUE)
    private String userName;
    /**
     * Пароль
     */
    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;
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
    @Column(name = "image", length = Integer.MAX_VALUE)
    private String image;
    /**
     * Объявления пользователя
     */
    @OneToMany(mappedBy = "user")
    private Set<Ads> ads = new LinkedHashSet<>();
    /**
     * Роли пользователя
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    public <E> Users(String username, String password, ArrayList<E> es) {
    }

    public Users() {

    }
}