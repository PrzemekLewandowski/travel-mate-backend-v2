package com.travelmate.controller.command;

import com.travelmate.service.command.AuthenticationCommandService;
import com.travelmate.viewmodel.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationCommandRestController {

    private final AuthenticationCommandService authenticationCommandService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        return authenticationCommandService.registerUser(signUpRequest);
    }
}
