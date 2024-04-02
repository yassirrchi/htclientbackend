package com.peaqockrh.peaqockrh.mappers;
import com.peaqockrh.peaqockrh.dto.UserDTO;
import com.peaqockrh.peaqockrh.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
