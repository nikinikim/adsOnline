package com.example.adsonline.DTOs;
import com.example.adsonline.enums.Roles;
import lombok.Data;

@Data
public class RegisterReqDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Roles role;
}
