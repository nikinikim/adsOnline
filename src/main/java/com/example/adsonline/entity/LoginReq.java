package com.example.adsonline.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class LoginReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String password;
    private String username;

    public LoginReq(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public LoginReq() {
    }
}
