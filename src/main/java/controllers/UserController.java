package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Создание нового пользователя
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterReq registerReq) {
        User newUser = userService.registerUser(registerReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    //Аутентификация пользователя и получение токена
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginReq loginReq) {
        TokenResponse tokenResponse = userService.loginUser(loginReq);
        return ResponseEntity.ok(tokenResponse);
    }

    //Обновление пароля
    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody NewPassword newPassword) {
        userService.setPassword(newPassword);
        return ResponseEntity.ok("Пароль успешно изменен");
    }

    //Получение информации о текущем пользователе
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        User currentUser = userService.updateUser(userUpdate);
        return ResponseEntity.ok(updatedUser);
    }

    //Обновление изображения текущего пользователя
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image){
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
}


