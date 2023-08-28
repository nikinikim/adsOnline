package com.example.adsonline.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "new_password")
public class NewPassword {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Пароль пользователя
     */
    @Column(name = "current_password", nullable = false)
    private String currentPassword;
    /**
     * Новый пароль пользователя
     */
    @Column(name = "newPassword", nullable = false)
    private String newPassword;

    public NewPassword() {
    }

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}