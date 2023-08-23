package controllers;

<<<<<<< HEAD:src/main/java/com/example/adsonline/controllers/UserController.java
import DTOs.NewPasswordDTO;
import DTOs.UserDTO;
import services.UserService;
=======
import entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
>>>>>>> Kristina_feature_23.08:src/main/java/controllers/UserController.java
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD:src/main/java/com/example/adsonline/controllers/UserController.java
=======
import services.AuthService2;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
>>>>>>> Kristina_feature_23.08:src/main/java/controllers/UserController.java

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private final AuthService2 authService2;
    private final UserService userService;

    public UserController(AuthService2 authService2, UserService userService) {
        this.authService2 = authService2;
        this.userService = userService;
    }

<<<<<<< HEAD:src/main/java/com/example/adsonline/controllers/UserController.java
//    //Создание нового пользователя
//    @PostMapping("/register")
//    public ResponseEntity<UserDTO> register(@RequestBody RegisterReqDTO registerReq) {
//
//        UserDTO newUser = userService.registerUser(registerReq);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }

//    //Аутентификация пользователя и получение токена
//    @PostMapping("/login")
//    public ResponseEntity<Object> login(@RequestBody LoginReqDTO loginReq) {
//
//        UserDTO user = userService.loginUser(loginReq);
//        return ResponseEntity.ok(user);
//    }
=======
>>>>>>> Kristina_feature_23.08:src/main/java/controllers/UserController.java

    //Обновление пароля
    @Operation(
            summary = "setPassword",
            description = "for change password account",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NewPasswordDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PostMapping(value = "/users/set_password",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<NewPasswordDTO> setPassword(
            @RequestBody @Validated NewPasswordDTO body) {
        if (userService.setPassword(body);){
            return ResponseEntity.ok(body);
        } else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    //Получение информации о текущем пользователе
    @Operation(summary = "getUser",
            description = "return info about user with authentication",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping(value = "me",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserById(authentication.getName());
    }

    //Обновление изображения текущего пользователя
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@PathVariable Long userId, @RequestParam("image") MultipartFile
            image) {

        userService.updateUserImage(userId, image);
        return ResponseEntity.ok().build();
    }
}

