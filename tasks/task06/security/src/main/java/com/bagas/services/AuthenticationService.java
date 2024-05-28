package com.bagas.services;

import com.bagas.entities.AuthenticationResponse;
import com.bagas.entities.Token;
import com.bagas.entities.User;
import com.bagas.mappers.TokenMapper;
import com.bagas.mappers.UserMapper;
import com.bagas.repositories.TokenRepository;
import com.bagas.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.bagas.constants.ExceptionMessageConstants.USER_NOT_FOUND_MESSAGE;
import static com.bagas.constants.CommonConstants.BEGINNING_AUTH_HEADER;
import static com.bagas.constants.CommonConstants.LOGIN_SUCCESSFUL_MESSAGE;
import static com.bagas.constants.CommonConstants.REGISTRATION_SUCCESSFUL_MESSAGE;
import static com.bagas.constants.CommonConstants.TOKEN_GENERATED_MESSAGE;
import static com.bagas.constants.CommonConstants.UNAUTHORIZED_MESSAGE;
import static com.bagas.constants.CommonConstants.USER_EXISTS_MESSAGE;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepo;

    public AuthenticationResponse register(User request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, null, USER_EXISTS_MESSAGE);
        }

        User user = createUser(request);
        user = userRepo.save(user);

        String accessToken = jwtService.generateAccessToken(user, null);
        String refreshToken = jwtService.generateRefreshToken(user, null);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken, REGISTRATION_SUCCESSFUL_MESSAGE);
    }

    public AuthenticationResponse authenticate(User requestedUser, HttpServletRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestedUser.getUsername(),
                        requestedUser.getPassword()
                )
        );

        String userIp = request.getRemoteAddr();
        User user = userRepo.findByUsername(requestedUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));

        String accessToken = jwtService.generateAccessToken(user, userIp);
        String refreshToken = jwtService.generateRefreshToken(user, userIp);

        revokeAndSaveToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken, LOGIN_SUCCESSFUL_MESSAGE);
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepo.findAllAccessTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> t.setLoggedOut(true));

        tokenRepo.saveAll(validTokens);
    }

    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = TokenMapper.createToken(accessToken, refreshToken, user);
        tokenRepo.save(token);
    }

    private User createUser(User request) {
        return UserMapper.createUser(request.getUsername(), passwordEncoder.encode(request.getPassword()),
                request.getAuthorities(), true, true,
                true, true);
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(authHeader) || !authHeader.startsWith((BEGINNING_AUTH_HEADER))) {
            return new AuthenticationResponse(null, null, UNAUTHORIZED_MESSAGE);
        }

        String token = authHeader
                .replace(BEGINNING_AUTH_HEADER, "")
                .trim();

        User user = retrieveUser(token);
        String ip = request.getRemoteAddr();

        if (jwtService.isRefreshTokenValid(token, user)) {
            String accessToken = jwtService.generateAccessToken(user, ip);
            String refreshToken = jwtService.generateRefreshToken(user, ip);

            revokeAndSaveToken(accessToken, refreshToken, user);

            return new AuthenticationResponse(accessToken, refreshToken, TOKEN_GENERATED_MESSAGE);
        }

        return new AuthenticationResponse(null, null, UNAUTHORIZED_MESSAGE);
    }

    private void revokeAndSaveToken(String accessToken, String refreshToken, User user) {
        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);
    }

    private User retrieveUser(String token) {
        String username = jwtService.extractUsername(token);
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
    }
}
