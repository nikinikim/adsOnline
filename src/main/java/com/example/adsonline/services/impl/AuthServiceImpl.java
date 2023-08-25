package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public UserDTO registerUser(RegisterReqDTO registerReq) {
        return null;
    }

    @Override
    public UserDTO login(LoginReqDTO loginReq) {
        return null;
    }
}
