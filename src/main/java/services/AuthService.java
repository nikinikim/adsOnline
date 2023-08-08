package services;

import DTOs.RegisterReqDTO;
import DTOs.RoleDTO;
import entity.RegisterReq;

public interface AuthService {
    RegisterReqDTO mapToDTO(RegisterReq registerReq);

    RegisterReq mapToEntity(RegisterReqDTO registerReqDTO);


        boolean login(String userName, String password);
        boolean register(RegisterReqDTO registerReqDTO, RoleDTO roleDTO);

}
