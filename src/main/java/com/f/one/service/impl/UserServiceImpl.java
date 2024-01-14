package com.f.one.service.impl;

import com.f.one.dto.AppResponse;
import com.f.one.dto.UserDto;
import com.f.one.entity.AppUser;
import com.f.one.repository.UserRepository;
import com.f.one.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppResponse createUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())){
            return  AppResponse.builder()
                    .responseMessage("Sorry, user with email already exists")
                    .build();
        }
        AppUser newUser = AppUser.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .age(userDto.getAge())
                .city(userDto.getCity())
                .country(userDto.getCountry())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

        userRepository.save(newUser);

        return AppResponse.builder()
                .responseMessage("User created successfully")
                .build();
    }
}
