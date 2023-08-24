package services;

<<<<<<< HEAD:src/main/java/com/example/adsonline/services/UserService.java
import DTOs.NewPasswordDTO;
import DTOs.UserDTO;
=======
import entity.User;
>>>>>>> Kristina_feature_23.08:src/main/java/services/UserService.java
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {

    User mapToEntity(UserDTO userDTO);

    User updateUser(User user);

    void setPassword(NewPasswordDTO newPassword);

    UserDTO mapToDTO(User user);

    Optional<User> getUserById(Long id);

    void updateUserImage(Long id, MultipartFile multipartFile);
}
