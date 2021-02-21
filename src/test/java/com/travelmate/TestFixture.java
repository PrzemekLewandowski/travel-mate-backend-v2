package com.travelmate;

import com.travelmate.model.*;
import com.travelmate.viewmodel.LoginForm;
import com.travelmate.viewmodel.SignUpForm;
import com.travelmate.viewmodel.UserViewModel;

import java.time.LocalDate;
import java.util.Set;

public final class TestFixture {

    private static final String NAME = "Name";
    private static final String USERNAME = "Username";
    private static final String CITY = "London";
    private static final int BORN_YEAR = 1990;
    private static final String EMAIL = "test@test.pl";
    private static final String PASSWORD = "password";
    private static final int BUDGET_VALUE_FROM = 1000;
    private static final int BUDGET_VALUE_TO = 4111;
    private static final String TITLE = "title";
    private static final String COUNTRY_NAME_POLAND = "Poland";
    private static final String COUNTRY_CODE_POLAND = "PL";
    private static final String COUNTRY_NAME_RUSSIA = "Russia";
    private static final String COUNTRY_CODE_RUSSIA = "RU";
    private static final int MAX_NUMBER_OF_PARTICIPANTS = 5;
    private static final String ENROLLED_PARTICIPANT_TOMEK = "Tomek";
    private static final String ENROLLED_PARTICIPANT_ANIA = "Ania";
    private static final String INFO_ABOUT_TRAVEL = "info";
    private static final String IMAGE_FILE_NAME = "test.jpg";
    private static final String COMMENT_TEXT = "comment";
    private static final String AVATAR_FILE_NAME = "avatar.jpg";
    private static final String INFO_ABOUT_USER = "info";
    private static final String COUNTRY_NAME_GEORGIA = "Georgia";
    private static final String COUNTRY_CODE_GEORGIA = "GE";
    private static final boolean IS_ACCOUNT_CLOSED = false;

    public static SignUpForm getSignUpForm() {
        return SignUpForm.builder()
                .name(NAME)
                .username(USERNAME)
                .city(CITY)
                .bornYear(BORN_YEAR)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    public static Post getPost() {
        return Post.builder()
                .budgetValueFrom(BUDGET_VALUE_FROM)
                .budgetValueTo(BUDGET_VALUE_TO)
                .title(TITLE)
                .dateFrom(LocalDate.now())
                .dateTo(LocalDate.now().plusDays(3))
                .countries(Set.of(
                        Country.builder()
                                .name(COUNTRY_NAME_POLAND)
                                .code(COUNTRY_CODE_POLAND)
                                .build(),
                        Country.builder()
                                .name(COUNTRY_NAME_RUSSIA)
                                .code(COUNTRY_CODE_RUSSIA)
                                .build()))
                .maxNumberOfParticipants(MAX_NUMBER_OF_PARTICIPANTS)
                .enrolledParticipants(Set.of(
                        User.builder()
                                .username(ENROLLED_PARTICIPANT_TOMEK)
                                .build(),
                        User.builder()
                                .username(ENROLLED_PARTICIPANT_ANIA)
                                .build()))
                .infoAboutTravel(INFO_ABOUT_TRAVEL)
                .postedByUsername(getUser())
                .imageFileName(IMAGE_FILE_NAME)
                .comments(Set.of(Comment.builder().commentText(COMMENT_TEXT).build()))
                .isActual(true)
                .build();
    }

    public static User getUser() {
        return User.builder()
                .bornYear(BORN_YEAR)
                .avatarFileName(AVATAR_FILE_NAME)
                .budgetValueFrom(BUDGET_VALUE_FROM)
                .budgetValueTo(BUDGET_VALUE_TO)
                .city(CITY)
                .infoAboutUser(INFO_ABOUT_USER)
                .email(EMAIL)
                .roles(Set.of(Role.builder().name(RoleName.ROLE_USER).build()))
                .username(USERNAME)
                .name(NAME)
                .preferredCountries(Set.of(Country.builder()
                        .name(COUNTRY_NAME_GEORGIA)
                        .code(COUNTRY_CODE_GEORGIA)
                        .build()))
                .isAccountClosed(false)
                .build();
    }

    public static LoginForm getLoginForm() {
        return LoginForm.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    public static UserViewModel getUserViewModel() {
        return UserViewModel.builder()
                .infoAboutUser(INFO_ABOUT_USER)
                .avatarFileName(AVATAR_FILE_NAME)
                .budgetValueFrom(BUDGET_VALUE_FROM)
                .budgetValueTo(BUDGET_VALUE_TO)
                .city(CITY)
                .bornYear(BORN_YEAR)
                .isAccountClosed(IS_ACCOUNT_CLOSED)
                .email(EMAIL)
                .name(NAME)
                .username(USERNAME)
                .build();
    }
}
