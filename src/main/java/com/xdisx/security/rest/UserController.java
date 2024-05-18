package com.xdisx.security.rest;

import com.xdisx.security.rest.request.UserRequestDto;
import com.xdisx.security.rest.response.UserResponseDto;
import com.xdisx.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xdisx/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponseDto addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Add user request: {}", userRequestDto);
        return userService.createUser(userRequestDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> loginUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Login user request: {}", userRequestDto);
        return userService.loginUser(userRequestDto);
    }
}
