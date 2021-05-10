package com.travelmate.service.query;

import com.travelmate.message.response.JwtResponse;
import com.travelmate.model.User;
import com.travelmate.repository.UserQueryRepository;
import com.travelmate.security.jwt.JwtProvider;
import com.travelmate.viewmodel.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationQueryService {

    private final UserQueryRepository userQueryRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public ResponseEntity<JwtResponse> authenticateUserAndGetJwtToken(LoginForm loginRequest) {
        String username = loginRequest.getUsername();
        Optional<User> userOptional = userQueryRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException(String.format("Użytkownik %s nie istnieje.", username));
        }
        User user = userOptional.get();
        if (user.isAccountClosed()) {
            throw new LockedException("Konto jest zamknięte.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Złe hasło.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtResponse jwtResponse = getJwtResponse(authentication);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private JwtResponse getJwtResponse(Authentication authentication) {
        String jsonWebToken = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return JwtResponse.builder()
                .token(jsonWebToken)
                .username(userDetails.getUsername())
                .authorities(userDetails.getAuthorities())
                .build();
    }
}
