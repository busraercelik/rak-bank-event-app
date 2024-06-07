package com.rakbank.busra.app.user.services;

import com.rakbank.busra.app.user.common.exceptions.ApplicationException;
import com.rakbank.busra.app.user.dtos.UserDTO;
import com.rakbank.busra.app.user.errors.ErrorCode;
import com.rakbank.busra.app.user.mappers.UserMapper;
import com.rakbank.busra.app.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDTO create(UserDTO dto) {
        var user = userMapper.userDTOToUser(dto);
        userRepository.saveAndFlush(user);
        return userMapper.userToUserDTO(user);
    }

    public UserDTO fetch(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        String.format("No user found with id %s", id), ErrorCode.USER_NOT_FOUND));
        return userMapper.userToUserDTO(user);
    }
}
