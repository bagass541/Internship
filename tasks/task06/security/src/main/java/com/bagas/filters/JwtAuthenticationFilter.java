package com.bagas.filters;

import com.bagas.services.JwtService;
import com.bagas.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.bagas.constants.CommonConstants.AUTHORIZATION_HEADER;
import static com.bagas.constants.CommonConstants.BEGINNING_AUTH_HEADER;
import static com.bagas.constants.CommonConstants.IP_CLAIM;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (Objects.isNull(authHeader) || !authHeader.startsWith(BEGINNING_AUTH_HEADER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader
                .replace(BEGINNING_AUTH_HEADER, "")
                .trim();

        String username = jwtService.extractUsername(jwt);

        String ip = request.getRemoteAddr();
        String tokenIp = jwtService.extractClaim(jwt, claims -> claims.get(IP_CLAIM, String.class));

        if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = this.userService.loadUserByUsername(username);

            if (jwtService.isAccessTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                if (Objects.isNull(tokenIp) || tokenIp.equals(ip)) {
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    request.logout();
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
