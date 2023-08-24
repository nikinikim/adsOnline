package services;

import DTOs.RoleDTO;
import DTOs.RegisterReqDTO;
import entity.RegisterReq;

public interface AuthService2 {
    RegisterReqDTO mapToDTO(RegisterReq registerReq);

    RegisterReq mapToEntity(RegisterReqDTO registerReqDTO);


        boolean login(String userName, String password);

    boolean register(RegisterReqDTO registerReqDTO, RoleDTO roleDTO);
}
