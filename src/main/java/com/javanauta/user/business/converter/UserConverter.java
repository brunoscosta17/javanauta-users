package com.javanauta.user.business.converter;

import com.javanauta.user.business.dto.AddressDTO;
import com.javanauta.user.business.dto.PhoneDTO;
import com.javanauta.user.business.dto.UserDTO;
import com.javanauta.user.infrastrucure.entity.Address;
import com.javanauta.user.infrastrucure.entity.Phone;
import com.javanauta.user.infrastrucure.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    // UserDTO -> User Entity
    public User toEntityUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(toAddressList(userDTO.getAddresses()))
                .phones(toPhonesList(userDTO.getPhones()))
                .build();
    }

    // AddressDTO -> List<Address>
    public List<Address> toAddressList(List<AddressDTO> addressesDTO) {
        return addressesDTO.stream()
                .map(this::toAddress)
                .toList();
    }

    // AddressDTO -> Address Entity
    public Address toAddress(AddressDTO addressDTO) {
        return Address.builder()
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .city(addressDTO.getCity())
                .complement(addressDTO.getComplement())
                .state(addressDTO.getState())
                .postal_code(addressDTO.getPostal_code())
                .build();
    }

    // PhoneDTO -> List<Phone>
    public List<Phone> toPhonesList(List<PhoneDTO> phonesDTO) {
        return phonesDTO.stream()
                .map(this::toPhone)
                .toList();
    }

    // PhoneDTO -> Phone Entity
    public Phone toPhone(PhoneDTO phoneDTO) {
        return Phone.builder()
                .number(phoneDTO.getNumber())
                .ddd(phoneDTO.getDdd())
                .build();
    }

    // User Entity -> UserDTO
    public UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(toAddressDTOList(user.getAddresses()))
                .phones(toPhoneDTOList(user.getPhones()))
                .build();
    }

    // List<Address> -> List<AddressDTO>
    public List<AddressDTO> toAddressDTOList(List<Address> addresses) {
        return addresses.stream()
                .map(this::toAddressDTO)
                .toList();
    }

    // Address Entity -> AddressDTO
    public AddressDTO toAddressDTO(Address addressEntity) {
        return AddressDTO.builder()
                .id(addressEntity.getId())
                .street(addressEntity.getStreet())
                .number(addressEntity.getNumber())
                .city(addressEntity.getCity())
                .complement(addressEntity.getComplement())
                .state(addressEntity.getState())
                .postal_code(addressEntity.getPostal_code())
                .build();
    }

    // List<Phone> -> List<PhoneDTO>
    public List<PhoneDTO> toPhoneDTOList(List<Phone> phones) {
        return phones.stream()
                .map(this::toPhoneDTO)
                .toList();
    }

    // Phone Entity -> PhoneDTO
    public PhoneDTO toPhoneDTO(Phone phoneEntity) {
        return PhoneDTO.builder()
                .id(phoneEntity.getId())
                .number(phoneEntity.getNumber())
                .ddd(phoneEntity.getDdd())
                .build();
    }

    public User updateUser(UserDTO userDTO, User userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userDTO.getName() != null ? userDTO.getName() : userEntity.getName())
                .password(userDTO.getPassword() != null ? userDTO.getPassword() : userEntity.getPassword())
                .email(userDTO.getEmail() != null ? userDTO.getEmail() : userEntity.getEmail())
                .addresses(userDTO.getAddresses() != null
                        ? toAddressList(userDTO.getAddresses())
                        : new ArrayList<>(userEntity.getAddresses()))
                .phones(userDTO.getPhones() != null
                        ? toPhonesList(userDTO.getPhones())
                        : new ArrayList<>(userEntity.getPhones()))
                .build();
    }

    public Address updateAddress(AddressDTO addressDTO, Address addressEntity) {
        return Address.builder()
                .id(addressEntity.getId())
                .street(addressDTO.getStreet() != null ? addressDTO.getStreet() : addressEntity.getStreet())
                .number(addressDTO.getNumber() != null ? addressDTO.getNumber() : addressEntity.getNumber())
                .complement(addressDTO.getComplement() != null ? addressDTO.getComplement() : addressEntity.getComplement())
                .city(addressDTO.getCity() != null ? addressDTO.getCity() : addressEntity.getCity())
                .state(addressDTO.getState() != null ? addressDTO.getState() : addressEntity.getState())
                .postal_code(addressDTO.getPostal_code() != null ? addressDTO.getPostal_code() : addressEntity.getPostal_code())
                .build();
    }

    public Phone updatePhone(PhoneDTO phoneDTO, Phone phoneEntity) {
        return Phone.builder()
                .id(phoneEntity.getId())
                .ddd(phoneDTO.getDdd() != null ? phoneDTO.getDdd() : phoneEntity.getDdd())
                .number(phoneDTO.getNumber() != null ? phoneDTO.getNumber() : phoneEntity.getNumber())
                .build();
    }

    public Address toAddressEntity(AddressDTO addressDTO, Long idUser) {
        return Address.builder()
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .postal_code(addressDTO.getPostal_code())
                .user_id(idUser)
                .build();
    }

    public Phone toPhoneEntity(PhoneDTO phoneDTO, Long idUser) {
        return Phone.builder()
                .ddd(phoneDTO.getDdd())
                .number(phoneDTO.getNumber())
                .user_id(idUser)
                .build();
    }
}