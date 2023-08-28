package com.example.adsonline.mappers;

import com.example.adsonline.DTOs.CommentDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.Comment;
import com.example.adsonline.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring"
)
public abstract class UserMapper implements GeneralMapper<User, UserDTO> {
    @Override
    @Mappings({
            @Mapping(source = "userDTO.", target = "user",
                    qualifiedByName = "userMapper")
    })
    public abstract User fromDto(UserDTO userDTO);

    @Override
    @Mapping(source = "user.id", target = "user")
    public abstract UserDTO toDto(User user);

    @Named("userMapper")
    User userMapper(Integer userId) {
        if (userId != null) {
            User user = new User();
            user.setId(Long.valueOf(userId));
            return user;
        }
        return null;
    }
}
