package com.bagas.services;

import com.bagas.entities.User;
import com.bagas.repositories.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.function.Function;

import static com.bagas.constants.CommonConstants.DEFAULT_ACCESS_TOKEN_EXPIRED_TIME;
import static com.bagas.constants.CommonConstants.DEFAULT_REFRESH_EXPIRED_TIME;
import static com.bagas.constants.CommonConstants.SECRET_KEY;
import static com.bagas.mappers.JwtTokenMapper.createJwtToken;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final TokenRepository tokenRepo;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private long extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration).getTime();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getPublicSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateAccessToken(User user, String ip) {
        return createJwtToken(user, DEFAULT_ACCESS_TOKEN_EXPIRED_TIME, ip, getPublicSignInKey());
    }

    public String generateRefreshToken(User user, String ip) {
        return createJwtToken(user, DEFAULT_REFRESH_EXPIRED_TIME, ip, getPublicSignInKey());
    }

    private boolean isTokenLoggedOut(String token) {
        return tokenRepo
                .findByAccessToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);
    }

    public boolean isAccessTokenValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token) && isTokenLoggedOut(token);
    }

    public boolean isRefreshTokenValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token) && isTokenLoggedOut(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token) < System.currentTimeMillis();
    }

    private SecretKey getPublicSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
