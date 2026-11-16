package com.javanauta.user.controller;

import com.javanauta.user.business.UserService;
import com.javanauta.user.business.dto.AddressDTO;
import com.javanauta.user.business.dto.PhoneDTO;
import com.javanauta.user.business.dto.UserDTO;
import com.javanauta.user.infrastrucure.entity.User;
import com.javanauta.user.infrastrucure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                        userDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UserDTO> searchUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUserData(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.updateUserData(token, userDTO));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO,
                                                    @RequestParam("id") Long addressId) {
        return ResponseEntity.ok(userService.updateAddress(addressId, addressDTO));
    }

    @PutMapping("/phone")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO phoneDTO,
                                                  @RequestParam("id") Long phoneId) {
        return ResponseEntity.ok(userService.updatePhone(phoneId, phoneDTO));
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.saveAddress(token, addressDTO));
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneDTO> savePhone(@RequestBody PhoneDTO phoneDTO,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.savePhone(token, phoneDTO));
    }

}
