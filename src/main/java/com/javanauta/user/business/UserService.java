package com.javanauta.user.business;

import com.javanauta.user.business.converter.UserConverter;
import com.javanauta.user.business.dto.UserDTO;
import com.javanauta.user.infrastrucure.entity.User;
import com.javanauta.user.infrastrucure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO) {
        User userEntity = userConverter.toEntityUser(userDTO);
        return userConverter.toUserDTO(userRepository.save(userEntity));
    }

}