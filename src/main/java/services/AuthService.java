package services;

import DTOs.LoginReqDTO;
import DTOs.RegisterReqDTO;
import DTOs.UserDTO;

public interface AuthService {
    UserDTO registerUser(RegisterReqDTO registerReq);

    UserDTO login(LoginReqDTO loginReq);

}
