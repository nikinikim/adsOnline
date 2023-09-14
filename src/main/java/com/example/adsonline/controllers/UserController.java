package com.example.adsonline.controllers;

import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.Users;
import com.example.adsonline.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи", description = "Пользователи")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
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
    public ResponseEntity<HttpStatus> setPassword(@RequestBody NewPasswordDTO newPassword, Principal principal) {
        newPassword.setNewPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userService.updateUserPassword(newPassword, principal);
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
                                    array = @ArraySchema(schema = @Schema(implementation = Users.class))
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
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        return ResponseEntity.ok(
                userService.getUser(principal));
    }

    @Operation(summary = "Обновление аватара пользователя",
            responses = {@ApiResponse(responseCode = "201",
                    description = "Аватар обновлён",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class))
                    )
            ),
                    @ApiResponse(responseCode = "401", description = "Нет авторизации")
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HttpStatus> updateUserImage(@RequestParam MultipartFile image, Principal principal) {
        userService.updateUserImage(principal, image);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized") })
    @PatchMapping(value = "/me", produces = { "application/json" }, consumes = { "application/json" })
    ResponseEntity<UserDTO> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema())
                                       @Valid
                                       @RequestBody UserDTO body,
                                       Principal principal) {
        return ResponseEntity.ok(userService.updateUser(body, principal));
    }
}



