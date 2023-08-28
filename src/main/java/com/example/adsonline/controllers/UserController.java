package com.example.adsonline.controllers;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.NewPassword;
import com.example.adsonline.entity.User;
import com.example.adsonline.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Контроллер для изменения пароля
     */

    @Operation(
            summary = "Обновление пароля пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Пароль обновлён",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    )
            }
    )
    @PostMapping("/set_password")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HttpStatus> setPassword(@RequestBody NewPassword newPassword) {
        userService.updateUserPassword(newPassword, (UserDetails)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    //Получение информации о текущем пользователе
    @Operation(
            summary = "Получение получение информации о пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Информация о пользователе получена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    )
            }
    )
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(
                userService.getUser((UserDetails)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal()));
    }

    @Operation(
            summary = "Обновление аватара пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Аватар обновлён",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    )
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HttpStatus> updateUserImage(@RequestParam MultipartFile image) {
        userService.updateUserImage(
                (UserDetails)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal(),
                image);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}


