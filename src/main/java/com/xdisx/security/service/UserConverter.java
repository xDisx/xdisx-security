package com.xdisx.security.service;

import com.xdisx.security.repository.db.UserEntity;
import com.xdisx.security.rest.response.UserResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {
    public static UserResponseDto toCreateResponse(UserEntity user) {
        return UserResponseDto.builder().id(user.getId()).username(user.getUsername()).build();
    }

    public static UserResponseDto toLoginResponse(UserEntity user, String token) {
        return UserResponseDto.builder().id(user.getId()).username(user.getUsername()).jwt(token).build();
    }
}
