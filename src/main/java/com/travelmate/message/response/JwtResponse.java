package com.travelmate.message.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Builder
public class JwtResponse {
    private static final String TYPE = "Bearer";
    private final String token;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
}
