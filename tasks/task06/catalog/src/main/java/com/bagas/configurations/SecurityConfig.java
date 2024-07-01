package com.bagas.configurations;

import com.bagas.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.bagas.constants.CommonConstants.SWAGGER_DOCS_ENDPOINTS;
import static com.bagas.constants.CommonConstants.SWAGGER_UI_ENDPOINTS;
import static com.bagas.constants.CommonConstants.WHOLE_CATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.WHOLE_CREATE_ENDPOINT;
import static com.bagas.constants.CommonConstants.WHOLE_DELETE_ENDPOINT;
import static com.bagas.constants.CommonConstants.WHOLE_GROUP_ENDPOINT;
import static com.bagas.constants.CommonConstants.WHOLE_PRODUCTS_BY_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.WHOLE_PRODUCT_ENDPOINT;
import static com.bagas.constants.CommonConstants.WHOLE_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.WHOLE_UPDATE_ENDPOINT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(WHOLE_GROUP_ENDPOINT, WHOLE_PRODUCT_ENDPOINT,
                            WHOLE_PRODUCTS_BY_SUBCATEGORY_ENDPOINT, WHOLE_CATEGORY_ENDPOINT,
                            WHOLE_SUBCATEGORY_ENDPOINT, SWAGGER_UI_ENDPOINTS, SWAGGER_DOCS_ENDPOINTS).permitAll();

                    authorize.requestMatchers(WHOLE_CREATE_ENDPOINT, WHOLE_UPDATE_ENDPOINT,
                            WHOLE_DELETE_ENDPOINT).authenticated();
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
