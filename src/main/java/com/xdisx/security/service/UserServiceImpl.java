package com.xdisx.security.service;

import com.xdisx.security.repository.UserRepository;
import com.xdisx.security.repository.db.UserEntity;
import com.xdisx.security.rest.request.UserRequestDto;
import com.xdisx.security.rest.response.JwtResponse;
import com.xdisx.security.rest.response.UserResponseDto;
import com.xdisx.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        String passwordHash = passwordEncoder.encode(userRequestDto.getPassword());
        UserEntity user = new UserEntity();
        user.setUsername(userRequestDto.getUsername());
        user.setPasswordHash(passwordHash);

        user = userRepository.saveAndFlush(user);

        return UserConverter.toCreateResponse(user);
    }

    @Override
    public ResponseEntity<?> loginUser(UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findByUsername(userRequestDto.getUsername()).orElseThrow();

        if (passwordEncoder.matches(userRequestDto.getPassword(), user.getPasswordHash())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
