package com.bagas.controllers;

import com.bagas.dto.UserDTO;
import com.bagas.entities.AuthenticationResponse;
import com.bagas.entities.User;
import com.bagas.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.bagas.constants.CommonConstants.JWT_LOGIN_ENDPOINT;
import static com.bagas.constants.CommonConstants.REFRESH_TOKEN_ENDPOINT;
import static com.bagas.constants.CommonConstants.REGISTER_ENDPOINT;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping(REGISTER_ENDPOINT)
    public ResponseEntity<HttpStatus> register(@RequestBody User request) {
        authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping(JWT_LOGIN_ENDPOINT)
    public AuthenticationResponse login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        return authService.authenticate(userDTO, request);
    }

    @PostMapping(REFRESH_TOKEN_ENDPOINT)
    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        return authService.refreshToken(request);
    }
}
