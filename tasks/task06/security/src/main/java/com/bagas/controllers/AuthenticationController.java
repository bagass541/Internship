package com.bagas.controllers;

import com.bagas.entities.AuthenticationResponse;
import com.bagas.entities.User;
import com.bagas.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAIN_DOMAIN)
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/jwt-login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user, HttpServletRequest request) {
        return ResponseEntity.ok(authService.authenticate(user, request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}
