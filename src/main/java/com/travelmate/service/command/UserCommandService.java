package com.travelmate.service.command;

import com.travelmate.mapper.UserMapper;
import com.travelmate.model.User;
import com.travelmate.repository.UserCommandRepository;
import com.travelmate.repository.UserQueryRepository;
import com.travelmate.service.exception.UserNotFoundException;
import com.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserCommandService {
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserViewModel> update(UserViewModel userViewModel) {
        User user = userMapper.toUser(userViewModel);
        userCommandRepository.save(user);
        return new ResponseEntity<>(userMapper.toUserViewModel(user), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> closeAccount(Authentication authentication, String password) {
        User user = userQueryRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("Nie można znaleźć użytkownika."));

        if (password == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Hasło się nie zgadza.");
        }
        user.closeAccount();
        userCommandRepository.save(user);
        return new ResponseEntity<>("Zamknięto konto.", HttpStatus.OK);
    }
}
