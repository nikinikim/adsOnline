package com.example.adsonline.services;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.RoleDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.RegisterReq;

public interface AuthService {

    boolean login(String userName, String password);

    boolean register(RegisterReqDTO registerReqDTO, RoleDTO roleDTO);

}
