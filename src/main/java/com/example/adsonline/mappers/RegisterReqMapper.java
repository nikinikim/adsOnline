package com.example.adsonline.mappers;

import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.RegisterReq;
import com.example.adsonline.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {UserMappers.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RegisterReqMapper {


    RegisterReq fromDto(RegisterReqDTO registerReqDTO);

    RegisterReqDTO toDto(RegisterReq registerReq);


}
