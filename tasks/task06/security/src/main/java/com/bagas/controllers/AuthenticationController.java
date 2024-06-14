package com.bagas.controllers;

import com.bagas.entities.AuthenticationResponse;
import com.bagas.entities.User;
import com.bagas.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public AuthenticationResponse register(@RequestBody User request) {
        return authService.register(request);
    }

    @PostMapping(JWT_LOGIN_ENDPOINT)
    public AuthenticationResponse login(@RequestBody User user, HttpServletRequest request) {
        return authService.authenticate(user, request);
    }

    @PostMapping(REFRESH_TOKEN_ENDPOINT)
    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        return authService.refreshToken(request);
    }
}
