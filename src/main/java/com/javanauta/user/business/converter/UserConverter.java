package com.javanauta.user.business.converter;

import com.javanauta.user.business.dto.AddressDTO;
import com.javanauta.user.business.dto.PhoneDTO;
import com.javanauta.user.business.dto.UserDTO;
import com.javanauta.user.infrastrucure.entity.Address;
import com.javanauta.user.infrastrucure.entity.Phone;
import com.javanauta.user.infrastrucure.entity.User;
import org.springframework.stereotype.Component;

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
    public AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .city(address.getCity())
                .complement(address.getComplement())
                .state(address.getState())
                .postal_code(address.getPostal_code())
                .build();
    }

    // List<Phone> -> List<PhoneDTO>
    public List<PhoneDTO> toPhoneDTOList(List<Phone> phones) {
        return phones.stream()
                .map(this::toPhoneDTO)
                .toList();
    }

    // Phone Entity -> PhoneDTO
    public PhoneDTO toPhoneDTO(Phone phone) {
        return PhoneDTO.builder()
                .number(phone.getNumber())
                .ddd(phone.getDdd())
                .build();
    }
}