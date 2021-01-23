package com.travelmate.mapper;

import com.travelmate.model.Country;
import com.travelmate.model.Role;
import com.travelmate.model.RoleName;
import com.travelmate.model.User;
import com.travelmate.viewmodel.UserViewModel;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserToViewModelMapperTest implements WithAssertions {
    private static User user;
    private static UserViewModel userViewModel;

    @BeforeAll
    static void setUp() {
        user = createTestUser();
        userViewModel = UserMapper.INSTANCE.toUserViewModel(user);
    }


    @Test
    void shouldMapUsernameToUserViewModel() {
        user = createTestUser();
        userViewModel = UserMapper.INSTANCE.toUserViewModel(user);
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
        assertThat(user.getPreferredCountries()).withFailMessage("Countries are wrong").isEqualTo(userViewModel.getPreferredCountries());
    }

    @Test
    void shouldMapEmailToUserViewModel() {
        assertThat(user.getEmail()).withFailMessage("Email is wrong").isEqualTo(userViewModel.getEmail());
    }

    @Test
    void shouldMapBudgetValueFromToUserViewModel() {
        assertThat(user.getBudgetValueFrom()).withFailMessage("Budget value from is wrong").isEqualTo(userViewModel.getBudgetValueFrom());
    }

    @Test
    void shouldMapBudgetValueToToUserViewModel() {
        assertThat(user.getBudgetValueTo()).withFailMessage("Budget value to is wrong").isEqualTo(userViewModel.getBudgetValueTo());
    }

    @Test
    void shouldMapInfoAboutUserToUserViewModel() {
        assertThat(user.getInfoAboutUser()).withFailMessage("Info about user is wrong").isEqualTo(userViewModel.getInfoAboutUser());
    }

    private static User createTestUser() {
        User user = new User();
        user.setBornYear(1990);
        user.setCity("Chicago");
        user.setEmail("test@gmail.com");
        user.setBudgetValueFrom(100);
        user.setBudgetValueTo(10000);
        user.setRoles(Set.of(new Role(RoleName.ROLE_USER)));
        user.setUsername("test");
        user.setName("test");
        user.setPreferredCountries(Set.of(new Country("Georgia", "GE")));
        user.setAvatarFileName("test.jpg");
        user.setInfoAboutUser("something");
        user.setIsAccountClosed(false);
        return user;
    }
}
