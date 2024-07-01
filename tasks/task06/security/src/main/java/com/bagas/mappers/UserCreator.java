package com.bagas.mappers;

import com.bagas.entities.Role;
import com.bagas.entities.User;

import java.util.Set;

import static com.bagas.utils.ParameterChecker.checkParameter;

public class UserCreator {

    public static User createUser(String username, String password, Set<Role> authorities,
                                  boolean isEnabled, boolean isAccountNonExpired,
                                  boolean isCredentialsNonExpired, boolean isAccountNonLocked) {

        return User.builder()
                .username(checkParameter(username))
                .password(checkParameter(password))
                .authorities(authorities)
                .isEnabled(isEnabled)
                .isAccountNonExpired(isAccountNonExpired)
                .isCredentialsNonExpired(isCredentialsNonExpired)
                .isAccountNonLocked(isAccountNonLocked)
                .build();
    }
}
