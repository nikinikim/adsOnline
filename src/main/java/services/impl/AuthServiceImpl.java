package services.impl;

import DTOs.LoginReq;
import DTOs.RegisterReq;
import DTOs.User;
import org.springframework.stereotype.Service;
import services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public User registerUser(RegisterReq registerReq) {
        return null;
    }

    @Override
    public User login(LoginReq loginReq) {
        return null;
    }
}
