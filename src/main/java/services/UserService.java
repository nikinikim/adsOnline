package services;

import DTOs.LoginReqDTO;
import DTOs.NewPasswordDTO;
import DTOs.RegisterReqDTO;
import DTOs.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserDTO registerUser(RegisterReqDTO registerReq);
    UserDTO loginUser(LoginReqDTO loginReq);
    UserDTO getUserById(int userId);
    UserDTO updateUser(UserDTO user);

    void setPassword(NewPasswordDTO newPassword);
    UserDTO updateUserImage(int id, MultipartFile image);
}
