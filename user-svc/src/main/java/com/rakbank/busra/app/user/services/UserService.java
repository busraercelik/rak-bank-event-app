package com.rakbank.busra.app.user.services;

import com.rakbank.busra.app.user.common.exceptions.ApplicationException;
import com.rakbank.busra.app.user.dtos.UserDTO;
import com.rakbank.busra.app.user.errors.ErrorCode;
import com.rakbank.busra.app.user.mappers.UserMapper;
import com.rakbank.busra.app.user.models.User;
import com.rakbank.busra.app.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.rakbank.busra.app.user.errors.ErrorCode.USER_ALREADY_REGISTERED;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User create(UserDTO dto) {
        var user = userMapper.toUser(dto);
        userRepository.findByEmailIgnoreCaseOrPhone(dto.getEmail(), dto.getPhone())
                .ifPresentOrElse(existingUser-> {
                    throw new ApplicationException("User is already taken", USER_ALREADY_REGISTERED);
                }, ()-> userRepository.saveAndFlush(user));

        return user;
    }

    public User fetch(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        String.format("No user found with id %s", id), ErrorCode.USER_NOT_FOUND));
    }
}
