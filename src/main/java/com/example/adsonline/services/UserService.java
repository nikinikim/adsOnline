package services;

import DTOs.NewPasswordDTO;
import DTOs.UserDTO;
import entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {
    UserDTO mapToDTO(User user);

    User mapToEntity(UserDTO userDTO);

    User updateUser(User user);

    void setPassword(NewPasswordDTO newPassword);

    Optional<User> getUserById(Long userId);

    void updateUserImage(Long id, MultipartFile multipartFile);
}
