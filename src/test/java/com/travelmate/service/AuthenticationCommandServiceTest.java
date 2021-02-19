package com.travelmate.service;

import com.travelmate.TestFixture;
import com.travelmate.service.exception.SignUpRequestValidationException;
import com.travelmate.viewmodel.SignUpForm;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class AuthenticationCommandServiceTest implements WithAssertions {

    @Autowired
    private AuthenticationCommandService authenticationCommandService;

    @Test
    void shouldCreateUser() {
        // given
        SignUpForm signUpForm = TestFixture.getSignUpForm();

        // when
        ResponseEntity<String> responseEntity = authenticationCommandService.registerUser(signUpForm);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldThrowSignUpRequestValidationException() {
        // given
        SignUpForm signUpForm = TestFixture.getSignUpForm();

        // when
        authenticationCommandService.registerUser(signUpForm);

        // then
        assertThatThrownBy(() -> authenticationCommandService.registerUser(signUpForm))
                .isInstanceOf(SignUpRequestValidationException.class);
    }
}
