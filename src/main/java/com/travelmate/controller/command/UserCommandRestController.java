package com.travelmate.controller.command;

import com.travelmate.service.command.UserCommandService;
import com.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserCommandRestController {
    private final UserCommandService userCommandService;

//    @PutMapping("/updateInfoAboutUser")
//    public ResponseEntity<UserViewModel> updateInfoAboutUser(@RequestBody UserViewModel userViewModel) {
//        return userCommandService.updateInfoAboutUser(userViewModel);
//    }
//
//    @PutMapping("/updateBudgetValue")
//    public ResponseEntity<UserViewModel> updateBudgetValue(@RequestBody UserViewModel userViewModel) {
//        return userCommandService.updateBudgetValue(userViewModel);
//    }

    @PutMapping("/updatePreferredCountries")
    public ResponseEntity<UserViewModel> updatePreferredCountries(@RequestBody UserViewModel userViewModel) {
        return userCommandService.updatePreferredCountries(userViewModel);
    }

    @PutMapping("/closeAccount")
    public ResponseEntity<String> closeAccount(Authentication authentication, @RequestBody String password) {
        return userCommandService.closeAccount(authentication, password);
    }
}
