package com.bagas.configurations;

import com.bagas.filters.JwtAuthenticationFilter;
import com.bagas.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.bagas.constants.CommonConstants.JWT_LOGIN_ENDPOINT;
import static com.bagas.constants.CommonConstants.LOGIN_ENDPOINT;
import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;
import static com.bagas.constants.CommonConstants.REGISTER_ENDPOINT;
import static com.bagas.constants.CommonConstants.SECURED_ENDPOINT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(REGISTER_ENDPOINT, JWT_LOGIN_ENDPOINT, LOGIN_ENDPOINT).permitAll();
                    authorize.anyRequest().authenticated();
                })
                .userDetailsService(userService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(Customizer.withDefaults())
                .formLogin(form -> form.defaultSuccessUrl(MAIN_DOMAIN + SECURED_ENDPOINT))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
