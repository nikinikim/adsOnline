package services.impl;

import DTOs.LoginReqDTO;
import DTOs.RegisterReqDTO;
import DTOs.UserDTO;
import org.springframework.stereotype.Service;
import services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public UserDTO registerUser(RegisterReqDTO registerReq) {
        return null;
    }

    @Override
    public UserDTO login(LoginReqDTO loginReq) {
        return null;
    }
}
