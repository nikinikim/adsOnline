package services;

import DTOs.LoginReq;
import DTOs.RegisterReq;
import DTOs.User;

public interface AuthService {
    User registerUser(RegisterReq registerReq);

    User login(LoginReq loginReq);

}
