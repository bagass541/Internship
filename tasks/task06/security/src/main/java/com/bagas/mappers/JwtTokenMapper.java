package com.bagas.mappers;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.bagas.constants.CommonConstants.IP_CLAIM;
import static com.bagas.constants.CommonConstants.ROLES_CLAIM;

public class JwtTokenMapper {

    public static String createJwtToken(UserDetails userDetails, long expireTime, String ip, SecretKey key) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .claim(ROLES_CLAIM, userDetails.getAuthorities().toString())
                .claim(IP_CLAIM, ip)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }
}
