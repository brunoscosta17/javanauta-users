package com.javanauta.user.business;

import com.javanauta.user.business.converter.UserConverter;
import com.javanauta.user.business.dto.UserDTO;
import com.javanauta.user.infrastrucure.entity.User;
import com.javanauta.user.infrastrucure.exceptions.ConflictException;
import com.javanauta.user.infrastrucure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastrucure.repository.UserRepository;
import com.javanauta.user.infrastrucure.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public UserDTO saveUser(UserDTO userDTO) {
        emailExists(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userEntity = userConverter.toEntityUser(userDTO);
        return userConverter.toUserDTO(userRepository.save(userEntity));
    }

    public void emailExists(String email) {
        if (verifyEmailExists(email)) {
            throw new ConflictException("E-mail already exists: " + email);
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

    public UserDTO updateUserData(String token, UserDTO userDTO) {
        String email = jwtUtil.extractEmail(token.substring(7));

        userDTO.setPassword(userDTO.getPassword() != null ? passwordEncoder.encode(userDTO.getPassword()) : null);

        User userEntity = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("E-mail not found"));

        User user = userConverter.updateUser(userDTO, userEntity);

        return userConverter.toUserDTO(userRepository.save(user));
    }

}