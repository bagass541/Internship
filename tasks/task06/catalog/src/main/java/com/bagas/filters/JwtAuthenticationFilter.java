package com.bagas.filters;

import com.bagas.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.bagas.constants.CommonConstants.AUTHORIZATION_HEADER;
import static com.bagas.constants.CommonConstants.BEGINNING_AUTH_HEADER;
import static com.bagas.constants.CommonConstants.IP_CLAIM;
import static com.bagas.constants.CommonConstants.ROLES_CLAIM;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (Objects.isNull(authHeader) || !authHeader.startsWith(BEGINNING_AUTH_HEADER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader
                .replace(BEGINNING_AUTH_HEADER, "")
                .trim();

        String ip = request.getRemoteAddr();
        String tokenIp = jwtService.extractClaim(jwt, claims -> claims.get(IP_CLAIM, String.class));
        String username = jwtService.extractUsername(jwt);

        String rolesInString = jwtService
                .extractClaim(jwt, claims -> claims.get(ROLES_CLAIM, String.class));

        rolesInString.replace("[", "").replace("]", "");
        String[] rolesString = rolesInString.split(",");

        List<SimpleGrantedAuthority> roles = Arrays.stream(rolesString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        if (!jwtService.isTokenExpired(jwt)) {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, roles);

            if (Objects.isNull(tokenIp) || tokenIp.equals(ip)) {
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                request.logout();
            }
        }

        filterChain.doFilter(request, response);
    }
}
