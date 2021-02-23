package com.travelmate.service.query;

import com.travelmate.mapper.UserMapper;
import com.travelmate.model.User;
import com.travelmate.repository.UserQueryRepository;
import com.travelmate.service.exception.UserNotFoundException;
import com.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserQueryRepository userQueryRepository;
    private final UserMapper userMapper;

    public ResponseEntity<UserViewModel> getCurrentUser(Authentication authentication) {
        Authentication currentUser = Optional.ofNullable(authentication)
                .orElseThrow(() -> new UserNotFoundException("Didn't found user"));
        String currentUserName = currentUser.getName();

        User user = userQueryRepository.findByUsername(currentUserName)
                .orElseThrow(() -> new UserNotFoundException(String.format("Didn't found user with username: %s", currentUserName)));
        return new ResponseEntity<>(userMapper.toUserViewModel(user), HttpStatus.OK);
    }
}
