package com.example.adsonline.DTOs;

import lombok.Data;

@Data
public class UserDTO {

    private String email;
    private String firstName;
    private int id;
    private String lastName;
    private String phone;
    private String regDate;
    private String city;
    private String image;
}
