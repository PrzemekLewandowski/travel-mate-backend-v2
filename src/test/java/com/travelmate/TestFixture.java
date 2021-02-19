package com.travelmate;

import com.travelmate.model.*;
import com.travelmate.viewmodel.SignUpForm;

import java.time.LocalDate;
import java.util.Set;

public final class TestFixture {

    public static SignUpForm getSignUpForm() {
        return SignUpForm.builder()
                .name("Name")
                .username("Username")
                .address("London")
                .bornYear(1990)
                .email("test@test.pl")
                .password("pass")
                .build();
    }

    public static Post getPost() {
        return Post.builder()
                .budgetValueFrom(1000)
                .budgetValueTo(4111)
                .title("title")
                .dateFrom(LocalDate.now())
                .dateTo(LocalDate.now().plusDays(3))
                .countries(Set.of(
                        Country.builder()
                                .name("Poland")
                                .code("PL")
                                .build(),
                        Country.builder()
                                .name("Russia")
                                .code("RU")
                                .build()))
                .maxNumberOfParticipants(5)
                .enrolledParticipants(Set.of(User.builder().username("Tomek").build(), User.builder()
                        .username("Ania")
                        .build()))
                .infoAboutTravel("info")
                .postedByUsername(User.builder().username("Przemek").build())
                .imageFileName("test.jpg")
                .comments(Set.of(Comment.builder().commentText("comment").build()))
                .isActual(true)
                .build();
    }

    public static User getUser() {
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
