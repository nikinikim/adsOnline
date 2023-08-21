package com.example.adsonline.services;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserDTO registerUser(RegisterReqDTO registerReq);
    UserDTO loginUser(LoginReqDTO loginReq);
    UserDTO getUserById(int userId);
    UserDTO updateUser(UserDTO user);

    void setPassword(NewPasswordDTO newPassword);
    UserDTO updateUserImage(int id, MultipartFile image);
}
