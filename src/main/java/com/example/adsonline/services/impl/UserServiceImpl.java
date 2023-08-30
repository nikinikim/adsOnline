package com.example.adsonline.services.impl;


import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.NewPassword;
import com.example.adsonline.entity.User;
import com.example.adsonline.exception.NotFoundInDataBaseException;
import com.example.adsonline.mappers.UserMappers;
import com.example.adsonline.repository.UserRepository;
import com.example.adsonline.services.UserService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.RequestEntity.patch;

@Service
@Data
public class UserServiceImpl implements UserService {
    private final UserMappers userMappers;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public List<UserDTO> getUserById(int user_Id) {
        return userRepository.findAllUser_Id(user_Id).stream().map(userMappers::toDto).collect(Collectors.toList());
    }
    @Override
    public User findUserByLogin(String username) {
        logger.debug("Find user entity by login, {}", username);
        return userRepository.findUserByUsername(username)
                .orElseThrow(NotFoundInDataBaseException::new);
    }
    @Override
    public User createOrUpdate(UserDetails userDetails, User updateUser) {
        User userEntity = findUserByLogin(userDetails.getUsername());
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        patch(String.valueOf(userEntity));
        logger.debug("User updated, {}", userEntity.getRegisterReq().getUsername());
        return updateUser;
    }

    @Override
    public User updateUserPassword(NewPassword newPassword, UserDetails userDetails) {
        User userEntity = findUserByLogin(userDetails.getUsername());
        newPassword.setNewPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        System.out.println(userEntity);
        logger.debug("User password updated");
        return userEntity;
    }

    @Override
    public void updateUserImage(UserDetails id, MultipartFile multipartFile) {
        String imageUser = "image.jpg";
        try {
            File imageFile = new File(imageUser);
            byte[] imageData = Files.readAllBytes(imageFile.toPath());
            ImageIcon imageIcon = new ImageIcon(imageData);
            JLabel imageLabel = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO getUser(UserDetails userDetails) {
        return userMappers.toDto(
                findUserByLogin(
                        userDetails.getUsername()));
    }
}


