package com.javanauta.user.business;

import com.javanauta.user.business.converter.UserConverter;
import com.javanauta.user.business.dto.UserDTO;
import com.javanauta.user.infrastrucure.entity.User;
import com.javanauta.user.infrastrucure.exceptions.ConflictException;
import com.javanauta.user.infrastrucure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDTO) {
        emailExists(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userEntity = userConverter.toEntityUser(userDTO);
        return userConverter.toUserDTO(userRepository.save(userEntity));
    }

    public void emailExists(String email) {
        try {
            boolean exists = verifyEmailExists(email);
            if(exists) throw new ConflictException("E-mail already exists." + email);
        } catch (ConflictException e) {
            throw new ConflictException("E-mail already exists." + e.getCause());
        }
    }

    public boolean verifyEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

}