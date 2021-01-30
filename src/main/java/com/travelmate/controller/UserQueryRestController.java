package com.travelmate.controller;

import com.travelmate.service.UserQueryService;
import com.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserQueryRestController {
    private final UserQueryService userService;

    @GetMapping("/get")
    public ResponseEntity<UserViewModel> getCurrentUser(Authentication authentication) {
        return userService.getCurrentUser(authentication);
    }
}
