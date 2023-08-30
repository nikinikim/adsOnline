package com.example.adsonline.mappers;

import com.example.adsonline.DTOs.LoginReqDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.entity.LoginReq;
import com.example.adsonline.entity.RegisterReq;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {UserMappers.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LoginReqMapper {


    LoginReq fromDto(LoginReqDTO loginReqDTO);

    LoginReqDTO toDto(LoginReq loginReq);


}
