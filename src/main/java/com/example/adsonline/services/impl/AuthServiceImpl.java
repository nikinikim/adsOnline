package com.example.adsonline.services.impl;

import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.enums.Roles;
import com.example.adsonline.services.AuthService;
import com.example.adsonline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;



    /**
     * Метод для авторизации пользователя
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        if (!userService.isExist(userName)) {
            return false;
        }
        UserDetails userDetails = userService.loadUserByUsername(userName);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);
    }

    /**
     * Метод для регистрации пользователя
     * @param registerReqDTO
     * @return
     */
    @Override
    public boolean register(RegisterReqDTO registerReqDTO) {
        userService.create(registerReqDTO);
        return true;
    }
}

