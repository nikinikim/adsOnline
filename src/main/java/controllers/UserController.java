package controllers;

import DTOs.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.UserService;

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

        User user = userService.loginUser(loginReq);
        return ResponseEntity.ok(user);
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
        //int currentUserId = getCurrentUserId(); - заменить на реальную логику

        User currentUser = userService.getUserById(1); //1 - для теста
        return ResponseEntity.ok(currentUser);
    }

    //Обновление изображения текущего пользователя
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@PathVariable int userId, @RequestParam("image") MultipartFile image){

        userService.updateUserImage(userId,image);
        return ResponseEntity.ok().build();
    }
}


