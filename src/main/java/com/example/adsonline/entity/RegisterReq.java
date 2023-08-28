package com.example.adsonline.entity;

import com.example.adsonline.DTOs.RoleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "register_req")
public class RegisterReq {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Логин пользователя
     */
    @Column(name = "user_name", nullable = false)
    private String username;
    /**
     * Пароль
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * Имя пользователя
     */
    @Column(name = "first_Name", nullable = false)
    private String firstName;
    /**
     * Фамилия пользователя
     */
    @Column(name = "last_Name", nullable = false)
    private String lastName;
    /**
     * Номер телефона пользователя
     */
    @Column(name = "phone", nullable = false)
    private String phone;
    /**
     * Роль пользователя
     */
    @Column(name = "role", nullable = false)
    private RoleDTO roleDTO;

    public RegisterReq() {
    }

    public RegisterReq(String username, String password, String firstName, String lastName, String phone, RoleDTO roleDTO) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.roleDTO = roleDTO;
    }
}
