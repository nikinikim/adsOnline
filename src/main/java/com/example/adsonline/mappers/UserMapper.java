package com.example.adsonline.mappers;

import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public abstract class UserMapper implements GeneralMapper<User, UserDTO> {
}
