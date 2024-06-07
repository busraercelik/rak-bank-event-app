package com.rakbank.busra.app.user.mappers;

import com.rakbank.busra.app.user.dtos.UserDTO;
import com.rakbank.busra.app.user.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDTOToUser(UserDTO requestDTO);
    UserDTO userToUserDTO(User request);
}