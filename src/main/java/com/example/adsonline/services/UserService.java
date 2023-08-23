package com.example.adsonline.services;

import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {

    User mapToEntity(UserDTO userDTO);

    User updateUser(User user);

    void setPassword(NewPasswordDTO newPassword);

    UserDTO mapToDTO(User user);

    Optional<User> getUserById(Long id);

    void updateUserImage(Long id, MultipartFile multipartFile);
}
