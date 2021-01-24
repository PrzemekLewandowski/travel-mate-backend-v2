package com.travelmate.mapper;

import com.travelmate.model.Country;
import com.travelmate.model.Role;
import com.travelmate.model.RoleName;
import com.travelmate.model.User;
import com.travelmate.viewmodel.UserViewModel;
import org.assertj.core.api.WithAssertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.TestInstance.Lifecycle;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UserToViewModelMapperTest implements WithAssertions {
    private User user;
    private UserViewModel userViewModel;

    @BeforeAll
    void setUp() {
        user = createTestUser();
        userViewModel = UserMapper.INSTANCE.toUserViewModel(user);
    }


    @Test
    void shouldMapUsernameToUserViewModel() {
        assertThat(user.getUsername()).withFailMessage("Username is wrong").isEqualTo(userViewModel.getUsername());
    }

    @Test
    void shouldMapNameToUserViewModel() {
        assertThat(user.getName()).withFailMessage("Name is wrong").isEqualTo(userViewModel.getName());
    }

    @Test
    void shouldMapCityToUserViewModel() {
        assertThat(user.getCity()).withFailMessage("City is wrong").isEqualTo(userViewModel.getCity());
    }

    @Test
    void shouldMapYearOfBirthToUserViewModel() {
        assertThat(user.getBornYear()).withFailMessage("Born year is wrong").isEqualTo(userViewModel.getBornYear());
    }

    @Test
    void shouldMapPreferredCountriesToUserViewModel() {
        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("id", "createdAt", "updatedAt")
                .build();
        assertThat(user.getPreferredCountries())
                .usingRecursiveComparison(configuration)
                .isEqualTo(userViewModel.getPreferredCountries());
    }

    @Test
    void shouldMapEmailToUserViewModel() {
        assertThat(user.getEmail()).withFailMessage("Email is wrong").isEqualTo(userViewModel.getEmail());
    }

    @Test
    void shouldMapBudgetValueFromToUserViewModel() {
        assertThat(user.getBudgetValueFrom()).withFailMessage("Budget value from is wrong")
                .isEqualTo(userViewModel.getBudgetValueFrom());
    }

    @Test
    void shouldMapBudgetValueToToUserViewModel() {
        assertThat(user.getBudgetValueTo()).withFailMessage("Budget value to is wrong")
                .isEqualTo(userViewModel.getBudgetValueTo());
    }

    @Test
    void shouldMapInfoAboutUserToUserViewModel() {
        assertThat(user.getInfoAboutUser()).withFailMessage("Info about user is wrong")
                .isEqualTo(userViewModel.getInfoAboutUser());
    }

    private User createTestUser() {
        return User.builder()
                .bornYear(1998)
                .avatarFileName("avatar.jpg")
                .budgetValueFrom(1000)
                .budgetValueTo(5000)
                .city("Chicago")
                .infoAboutUser("info")
                .email("test@test.com")
                .roles(Set.of(Role.builder().name(RoleName.ROLE_USER).build()))
                .username("test")
                .name("test")
                .preferredCountries(Set.of(Country.builder().name("Georgia").code("GE").build()))
                .isAccountClosed(false)
                .build();
    }
}
