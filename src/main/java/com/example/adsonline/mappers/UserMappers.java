package com.example.adsonline.mappers;

import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMappers {

    User fromDto(UserDTO userDTO);

    UserDTO toDto(User user);


}
