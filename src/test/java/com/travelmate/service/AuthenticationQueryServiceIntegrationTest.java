package com.travelmate.service;

import com.travelmate.TestFixture;
import com.travelmate.mapper.UserMapper;
import com.travelmate.model.RoleName;
import com.travelmate.model.User;
import com.travelmate.repository.UserCommandRepository;
import com.travelmate.service.command.AuthenticationCommandService;
import com.travelmate.service.query.AuthenticationQueryService;
import com.travelmate.viewmodel.LoginForm;
import com.travelmate.viewmodel.SignUpForm;
import com.travelmate.viewmodel.UserViewModel;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class AuthenticationQueryServiceIntegrationTest implements WithAssertions {

    @Autowired
    private AuthenticationQueryService authenticationQueryService;

    @Autowired
    private AuthenticationCommandService authenticationCommandService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCommandRepository userCommandRepository;

    @Test
    void shouldAuthenticateUser() {
        // given
        SignUpForm signUpForm = TestFixture.getSignUpForm();
        authenticationCommandService.registerUser(signUpForm);

        // when
        ResponseEntity<String> responseEntity = authenticationQueryService.authenticateUser(TestFixture.getLoginForm());

        // then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains(RoleName.ROLE_USER.toString());
    }

    @Test
    void shouldThrowAuthenticationCredentialsNotFoundException() {
        // given
        LoginForm loginForm = LoginForm.builder().username("Username1").password("password").build();

        // when
        Exception exception = assertThrows(AuthenticationCredentialsNotFoundException.class,
                () -> authenticationQueryService.authenticateUser(loginForm));

        // then
        assertThat(exception.getMessage()).isEqualTo("Użytkownik Username1 nie istnieje.");
    }

    @Test
    void shouldThrowLockedException() {
        // given
        LoginForm loginForm = TestFixture.getLoginForm();
        UserViewModel userViewModel = TestFixture.getUserViewModel();
        userViewModel.setIsAccountClosed(true);
        User user = userMapper.toUser(userViewModel);
        userCommandRepository.save(user);

        // when
        Exception exception = assertThrows(LockedException.class,
                () -> authenticationQueryService.authenticateUser(loginForm));

        // then
        assertThat(exception.getMessage()).isEqualTo("Konto jest zamknięte.");
    }

    @Test
    void shouldThrowBadCredentialsException() {
        // given
        SignUpForm signUpForm = TestFixture.getSignUpForm();
        authenticationCommandService.registerUser(signUpForm);
        LoginForm loginForm = LoginForm.builder().username("Username").password("password1").build();


        // when
        Exception exception = assertThrows(BadCredentialsException.class,
                () -> authenticationQueryService.authenticateUser(loginForm));

        // then
        assertThat(exception.getMessage()).isEqualTo("Złe hasło.");
    }
}
