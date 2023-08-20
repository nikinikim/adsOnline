package com.example.adsonline.DTOs;

import entity.NewPassword;
import entity.RegisterReq;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private String email;
    private String firstName;
    private int id;
    private String lastName;
    private String phone;
    private String regDate;
    private String city;
    private String image;
    private NewPassword newPassword;
    private RegisterReq registerReq;


}
