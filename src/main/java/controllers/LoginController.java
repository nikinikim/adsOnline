package controllers;

<<<<<<< HEAD:src/main/java/com/example/adsonline/controllers/LoginController.java
package com.example.adsonline.controllers;

import DTOs.LoginReqDTO;
import DTOs.RegisterReqDTO;
import DTOs.UserDTO;
import services.AuthService;
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
=======
package controllers;

import DTOs.LoginReqDTO;
import DTOs.RegisterReqDTO;
import DTOs.UserDTO;
import services.AuthService;
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
>>>>>>> Kristina_feature_23.08:src/main/java/controllers/LoginController.java
