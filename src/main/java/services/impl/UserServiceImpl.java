package services.impl;

import DTOs.LoginReq;
import DTOs.RegisterReq;
import DTOs.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User registerUser(RegisterReq registerReq) {
        return null;
    }

    @Override
    public User loginUser(LoginReq loginReq) {
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return new User();
    }

    @Override
    public User updateUser(User user) {
        return user;
    }

    @Override
    public User updateUserImage(int id, MultipartFile image) {
        return new User();
    }
}
