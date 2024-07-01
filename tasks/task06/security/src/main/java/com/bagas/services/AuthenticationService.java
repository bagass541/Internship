package com.bagas.services;

import com.bagas.dto.UserDTO;
import com.bagas.entities.AuthenticationResponse;
import com.bagas.entities.Role;
import com.bagas.entities.Token;
import com.bagas.entities.User;
import com.bagas.exceptions.UserExistsException;
import com.bagas.mappers.TokenCreator;
import com.bagas.mappers.UserCreator;
import com.bagas.repositories.RoleRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

import static com.bagas.constants.CommonConstants.BEGINNING_AUTH_HEADER;
import static com.bagas.constants.CommonConstants.LOGIN_SUCCESSFUL_MESSAGE;
import static com.bagas.constants.CommonConstants.TOKEN_GENERATED_MESSAGE;
import static com.bagas.constants.CommonConstants.UNAUTHORIZED_MESSAGE;
import static com.bagas.constants.CommonConstants.USER_EXISTS_MESSAGE;
import static com.bagas.constants.ExceptionMessageConstants.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepo;

    public void register(User request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new UserExistsException(USER_EXISTS_MESSAGE);
        }

        User user = createUser(request);

        Set<Role> roles = user.getAuthorities().stream()
                .map(role -> roleRepo.findByAuthority(role.getAuthority()).orElse(new Role(role.getAuthority())))
                .collect(Collectors.toSet());

        user.setAuthorities(roles);
        userRepo.save(user);
    }

    public AuthenticationResponse authenticate(UserDTO requestedUserDTO, HttpServletRequest request) {
       authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestedUserDTO.getUsername(),
                        requestedUserDTO.getPassword(),
                        null
                )
        );

        String userIp = request.getRemoteAddr();
        User user = userRepo.findByUsername(requestedUserDTO.getUsername())
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
        Token token = TokenCreator.createToken(accessToken, refreshToken, user);
        tokenRepo.save(token);
    }

    private User createUser(User request) {
        return UserCreator.createUser(request.getUsername(), passwordEncoder.encode(request.getPassword()),
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
