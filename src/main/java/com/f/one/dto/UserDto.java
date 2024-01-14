package com.f.one.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String country;
    private String city;
    private byte age;
}
