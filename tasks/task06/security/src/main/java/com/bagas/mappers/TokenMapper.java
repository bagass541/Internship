package com.bagas.mappers;

import com.bagas.entities.Token;
import com.bagas.entities.User;

import static com.bagas.utils.ParameterChecker.checkParameter;

public class TokenMapper {

    public static Token createToken(String accessToken, String refreshToken, User user) {
        return Token.builder()
                .accessToken(checkParameter(accessToken))
                .refreshToken(checkParameter(refreshToken))
                .loggedOut(false)
                .user(checkParameter(user))
                .build();
    }
}
