package com.example.adsonline.services;

import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.NewPassword;
import com.example.adsonline.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<UserDTO> getUserById(int user_Id);

    User findUserByLogin(String username);

    User createOrUpdate(UserDetails userDetails, User updateUser);

    User updateUserPassword(NewPassword newPassword, UserDetails userDetails);

    void updateUserImage(UserDetails id, MultipartFile multipartFile);

    UserDTO getUser(UserDetails userDetails);
}
