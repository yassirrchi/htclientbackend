package com.peaqockrh.peaqockrh.mappers;
import com.peaqockrh.peaqockrh.dto.UserDTO;
import com.peaqockrh.peaqockrh.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
