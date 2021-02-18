package com.travelmate.service;

import com.travelmate.mapper.UserMapper;
import com.travelmate.model.Role;
import com.travelmate.model.RoleName;
import com.travelmate.model.User;
import com.travelmate.repository.RoleQueryRepository;
import com.travelmate.repository.UserCommandRepository;
import com.travelmate.repository.UserQueryRepository;
import com.travelmate.service.exception.RoleNotFoundException;
import com.travelmate.service.exception.SignUpRequestValidationException;
import com.travelmate.viewmodel.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthenticationCommandService {

    private static final int MINIMUM_AGE_NUMBER = 18;
    private static final int MAX_YEAR_OF_BIRTH = 1900;
    private static final int MIN_YEAR_OF_BIRTH = Calendar.getInstance().get(Calendar.YEAR) - MINIMUM_AGE_NUMBER;
    private final UserQueryRepository userQueryRepository;
    private final RoleQueryRepository roleQueryRepository;
    private final UserCommandRepository userCommandRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<String> registerUser(SignUpForm signUpRequest) {
        validateRequest(signUpRequest);
        User user = createUser(signUpRequest);
        userCommandRepository.save(user);
        return new ResponseEntity<>("Rejestracja przebiegła pomyślnie.", HttpStatus.CREATED);
    }

    private User createUser(SignUpForm signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Role userRole = roleQueryRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException("Nie znaleziono roli użytkownika"));
        signUpRequest.setRoles(Set.of(userRole));
        return userMapper.toUser(signUpRequest);
    }

    private void validateRequest(SignUpForm signUpRequest) {
        if (Boolean.TRUE.equals(userQueryRepository.existsByUsername(signUpRequest.getUsername()))) {
            throw new SignUpRequestValidationException("Podana nazwa użytkownika jest już zajęta.");
        } else if (Boolean.TRUE.equals(userQueryRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new SignUpRequestValidationException("Podana email jest już używany.");
        } else if (signUpRequest.getBornYear() >= MIN_YEAR_OF_BIRTH || signUpRequest.getBornYear() <= MAX_YEAR_OF_BIRTH) {
            throw new SignUpRequestValidationException(String.format("Podany rok urodzenia musi mieścić się w przedziale %d-%d", MAX_YEAR_OF_BIRTH, MIN_YEAR_OF_BIRTH));
        }
    }
}
