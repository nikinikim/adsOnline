package controllers;

import DTOs.*;
import entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.AuthService;
import services.UserService;

import java.util.Optional;

import static DTOs.RoleDTO.USER;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    //Обновление пароля
    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPasswordDTO newPassword) {

        userService.setPassword(newPassword);
        return ResponseEntity.ok("Пароль успешно изменен");
    }

    //Получение информации о текущем пользователе
    @GetMapping("/me")
    public ResponseEntity<Optional<User>> getUser(Long id) {
        //int currentUserId = getCurrentUserId(); - заменить на реальную логику

        Optional<User> currentUser = userService.getUserById(id); //1 - для теста
        return ResponseEntity.ok(currentUser);
    }

    //Обновление изображения текущего пользователя
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@PathVariable Long userId, @RequestParam("image") MultipartFile image){

        userService.updateUserImage(userId,image);
        return ResponseEntity.ok().build();
    }
}


