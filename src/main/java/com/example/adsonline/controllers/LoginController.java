package com.example.adsonline.controllers;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Авторизация")
public class LoginController {
    private final AuthService authService;

    @Operation(summary = "Авторизация пользователя", description = "")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDTO login) {
        if (authService.login(login) != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

        //Создание нового пользователя
    @Operation(summary = "Создание нового пользователя", description = "")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterReqDTO registerReq) {

//        UserDTO newUser = userService.registerUser(registerReq);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        return null;
    }
}
