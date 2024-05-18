package com.xdisx.security.service;

import com.xdisx.security.rest.request.UserRequestDto;
import com.xdisx.security.rest.response.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    ResponseEntity<?> loginUser(UserRequestDto userRequestDto);
}
