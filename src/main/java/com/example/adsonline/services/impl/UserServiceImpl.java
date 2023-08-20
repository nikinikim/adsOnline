package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO registerUser(RegisterReqDTO registerReq) {
        return null;
    }

    @Override
    public UserDTO loginUser(LoginReqDTO loginReq) {
        return null;
    }

    @Override
    public UserDTO getUserById(int userId) {
        return new UserDTO();
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return user;
    }

    @Override
    public void setPassword(NewPasswordDTO newPassword) {
        System.out.println("Пароль успешно изменен");
    }

    @Override
    public UserDTO updateUserImage(int id, MultipartFile image) {
        return new UserDTO();
    }
}
