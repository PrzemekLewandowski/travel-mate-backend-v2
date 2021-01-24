package com.travelmate.mapper;

import com.travelmate.model.Comment;
import com.travelmate.model.Country;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostToViewModelTest implements WithAssertions {
    private Post post;
    private PostViewModel postViewModel;

    @BeforeAll
    void setUp() {
        post = createTestPost();
        postViewModel = PostMapper.INSTANCE.toPostViewModel(post);
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
        assertThat(post.getFileName()).withFailMessage("File name is wrong.")
                .isEqualTo(postViewModel.getFileName());
    }

    @Test
    void shouldMapIsActualToPostViewModel() {
        assertThat(post.getIsActual()).isEqualTo(postViewModel.getIsActual());
    }

    private Post createTestPost() {
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
                .fileName("test.jpg")
                .comments(Set.of(Comment.builder().commentText("comment").build()))
                .isActual(true)
                .build();
    }
}
