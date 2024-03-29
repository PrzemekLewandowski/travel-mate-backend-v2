package com.travelmate.controller.query;

import com.travelmate.message.response.JwtResponse;
import com.travelmate.service.query.AuthenticationQueryService;
import com.travelmate.viewmodel.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationQueryRestController {

    private final AuthenticationQueryService authenticationQueryService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        return authenticationQueryService.authenticateUserAndGetJwtToken(loginRequest);
    }
}
