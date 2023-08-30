package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.RoleDTO;
import com.example.adsonline.entity.RegisterReq;
import com.example.adsonline.services.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsManager manager) {
        this.manager = manager;
        this.encoder = new BCryptPasswordEncoder();
    }
    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    @Override
    public boolean register(RegisterReqDTO registerReqDTO, RoleDTO roleDTO) {
        if (manager.userExists(registerReqDTO.getUsername())) {
            return false;
        }
        //noinspection deprecation
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .password(registerReqDTO.getPassword())
                        .username(registerReqDTO.getUsername())
                        .roles(roleDTO.name())
                        .build()
        );
        return true;
    }
}

