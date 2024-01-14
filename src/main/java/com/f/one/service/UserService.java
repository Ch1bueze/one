package com.f.one.service;

import com.f.one.dto.AppResponse;
import com.f.one.dto.UserDto;

public interface UserService {
 AppResponse createUser(UserDto userDto);
}
