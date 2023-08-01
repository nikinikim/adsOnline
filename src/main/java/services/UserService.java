package services;

import DTOs.LoginReq;
import DTOs.RegisterReq;
import DTOs.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User registerUser(RegisterReq registerReq);
    User loginUser(LoginReq loginReq);
    User getUserById(int userId);
    User updateUser(User user);
    User updateUserImage(int id, MultipartFile image);
}
