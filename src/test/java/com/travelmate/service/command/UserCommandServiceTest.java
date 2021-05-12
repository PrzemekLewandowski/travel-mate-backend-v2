package com.travelmate.service.command;

import com.travelmate.TestFixture;
import com.travelmate.mapper.UserMapper;
import com.travelmate.model.Country;
import com.travelmate.model.User;
import com.travelmate.repository.UserQueryRepository;
import com.travelmate.viewmodel.UserViewModel;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class UserCommandServiceTest implements WithAssertions {

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private UserMapper userMapper;

    @Test
    void shouldUpdatePreferredCountriesForUser() {
        // given
        User user = TestFixture.getUser();
        UserViewModel userViewModel = userMapper.toUserViewModel(user);
        userViewModel.setPreferredCountries(TestFixture.getCountriesViewModel());

        // when
        ResponseEntity<UserViewModel> responseEntity = userCommandService.update(userViewModel);
        User updatedUser = userQueryRepository.findByUsername(user.getUsername()).orElse(null);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getPreferredCountries()).extracting(Country::getName)
                .containsAll(Arrays.asList("Poland", "Russia", "Brazil"));
    }
}
