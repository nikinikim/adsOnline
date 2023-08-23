package com.example.adsonline.services;

import DTOs.RoleDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.entity.RegisterReq;

public interface AuthService2 {
    RegisterReqDTO mapToDTO(RegisterReq registerReq);

    RegisterReq mapToEntity(RegisterReqDTO registerReqDTO);


        boolean login(String userName, String password);

    boolean register(RegisterReqDTO registerReqDTO, RoleDTO roleDTO);
}
