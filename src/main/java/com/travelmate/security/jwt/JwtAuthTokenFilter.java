package com.travelmate.security.jwt;

import com.travelmate.security.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthTokenFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_HEADER = "Bearer ";

    private final JwtProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwt(request);
            if (StringUtils.isNotEmpty(jwt) && tokenProvider.validateJwtToken(jwt)) {
                String username = tokenProvider.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails
                        .getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            LOGGER.warn("Cannot set authentication: ", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(BEARER_HEADER)) {
            return authHeader.replace(BEARER_HEADER, StringUtils.EMPTY);
        }
        return null;
    }
}
