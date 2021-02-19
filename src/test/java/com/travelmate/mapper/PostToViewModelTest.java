package com.travelmate.mapper;

import com.travelmate.TestFixture;
import com.travelmate.model.Post;
import com.travelmate.model.User;
import com.travelmate.viewmodel.PostViewModel;
import com.travelmate.viewmodel.UserViewModel;
import org.assertj.core.api.WithAssertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostToViewModelTest implements WithAssertions {
    private Post post;
    private PostViewModel postViewModel;

    @Autowired
    private PostMapper postMapper;

    @BeforeAll
    void setUp() {
        post = TestFixture.getPost();
        postViewModel = postMapper.toPostViewModel(post);
    }

    @Test
    void shouldMapBudgetValueFromToPostViewModel() {
        assertThat(post.getBudgetValueFrom()).withFailMessage("Budget value from is wrong.")
                .isEqualTo(postViewModel.getBudgetValueFrom());
    }

    @Test
    void shouldMapBudgetValueToToPostViewModel() {
        assertThat(post.getBudgetValueTo()).withFailMessage("Budget value to is wrong.")
                .isEqualTo(postViewModel.getBudgetValueTo());
    }

    @Test
    void shouldMapTitleToPostViewModel() {
        assertThat(post.getTitle()).withFailMessage("Title is wrong.").isEqualTo(postViewModel.getTitle());
    }

    @Test
    void shouldMapTravelDateFromToPostViewModel() {
        assertThat(post.getDateFrom()).withFailMessage("Travel date from is wrong.")
                .isEqualTo(postViewModel.getDateFrom());
    }

    @Test
    void shouldMapTravelDateToToPostViewModel() {
        assertThat(post.getDateTo()).withFailMessage("Travel date to is wrong.").isEqualTo(postViewModel.getDateTo());
    }

    @Test
    void shouldMapCountriesToPostViewModel() {
        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("id", "createdAt", "updatedAt")
                .build();
        assertThat(post.getCountries()).usingRecursiveComparison(configuration).isEqualTo(postViewModel.getCountries());
    }

    @Test
    void shouldMapMaxNumberOfParticipantsToPostViewModel() {
        assertThat(post.getMaxNumberOfParticipants()).withFailMessage("Max number of participants is wrong.")
                .isEqualTo(postViewModel.getMaxNumberOfParticipants());
    }

    @Test
    void shouldMapCommentsToPostViewModel() {
        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("id", "createdAt", "updatedAt", "post")
                .build();
        assertThat(post.getComments())
                .usingRecursiveComparison(configuration)
                .isEqualTo(postViewModel.getComments());
    }

    @Test
    void shouldMapEnrolledParticipantsToPostViewModel() {
        assertThat(postViewModel.getEnrolledParticipants()).hasOnlyElementsOfType(UserViewModel.class);
        Set<String> postEnrolledUserNames = post.getEnrolledParticipants()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toSet());
        Set<String> viewModelPostEnrolledUserNames = postViewModel.getEnrolledParticipants()
                .stream()
                .map(UserViewModel::getUsername)
                .collect(Collectors.toSet());
        assertThat(postEnrolledUserNames).containsAll(viewModelPostEnrolledUserNames);
    }

    @Test
    void shouldMapInfoAboutTravelToPostViewModel() {
        assertThat(post.getInfoAboutTravel()).withFailMessage("Info about travel is wrong.")
                .isEqualTo(postViewModel.getInfoAboutTravel());
    }

    @Test
    void shouldMapPostedByUsernameToPostViewModel() {
        assertThat(postViewModel.getPostedByUsername()).isInstanceOf(UserViewModel.class);
        assertThat(post.getPostedByUsername().getUsername()).isEqualTo(postViewModel.getPostedByUsername()
                .getUsername());
    }

    @Test
    void shouldMapFileNameToPostViewModel() {
        assertThat(post.getImageFileName()).withFailMessage("File name is wrong.")
                .isEqualTo(postViewModel.getImageFileName());
    }

    @Test
    void shouldMapIsActualToPostViewModel() {
        assertThat(post.getIsActual()).isEqualTo(postViewModel.getIsActual());
    }
}
