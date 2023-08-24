package mappers;

import DTOs.UserDTO;
import entity.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public abstract class UserMapper implements GeneralMapper<User, UserDTO> {
}
