package com.travelmate.security.services;


import com.travelmate.model.User;
import com.travelmate.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserQueryRepository userQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userQueryRepository.findByUsernameIgnoreCase(username).orElseThrow(
                () -> new UsernameNotFoundException("Nie znaleziono użytkownika: " + username));
        return UserPrinciple.build(user);
    }
}
