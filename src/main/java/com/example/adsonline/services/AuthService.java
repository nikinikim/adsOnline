package com.example.adsonline.services;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;

public interface AuthService {
    UserDTO registerUser(RegisterReqDTO registerReq);

    UserDTO login(LoginReqDTO loginReq);

}
