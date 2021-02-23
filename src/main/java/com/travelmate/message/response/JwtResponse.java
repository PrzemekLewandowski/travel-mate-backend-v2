package com.travelmate.message.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class JwtResponse {
    private static final String TYPE = "Bearer";
    private final String token;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
}
