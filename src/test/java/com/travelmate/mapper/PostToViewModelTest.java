package com.travelmate.mapper;

import com.travelmate.model.Comment;
import com.travelmate.model.Country;
import com.travelmate.model.Post;
import com.travelmate.model.User;
import com.travelmate.viewmodel.PostViewModel;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostToViewModelTest implements WithAssertions {
    private Post post;
    private PostViewModel postViewModel;

    @BeforeAll
    void setUp() {
        post = createPost();
        postViewModel = PostMapper.INSTANCE.toPostViewModel(post);
    }

    @Test
    void shouldMapBudgetValueFromToPostViewModel() {
        assertThat(post.getBudgetValueFrom()).withFailMessage("Budget value from is wrong.").isEqualTo(postViewModel.getBudgetValueFrom());
    }
    @Test
    void shouldMapBudgetValueToToPostViewModel() {
        assertThat(post.getBudgetValueTo()).withFailMessage("Budget value to is wrong.").isEqualTo(postViewModel.getBudgetValueTo());
    }

    @Test
    void shouldMapCommentsToPostViewModel() {
        assertThat(post.getComments().size()).withFailMessage("Wrong amount of comments").isEqualTo(postViewModel.getBudgetValueFrom());
    }


    private Post createPost() {
        Post post = new Post();
        post.setBudgetValueFrom(1000);
        post.setBudgetValueTo(5000);
        post.setComments(Set.of(new Comment()));
        post.setCountries(Set.of(new Country()));
        post.setInfoAboutTravel("info");
        post.setIsActual(true);
        post.setTitle("title");
        post.setFileName("file name");
        post.setEnrolledParticipants(Set.of(new User()));
        post.setMaxNumberOfParticipants(3);
        post.setTravelDateFrom(LocalDate.now());
        post.setTravelDateTo(LocalDate.now().plusDays(2));
        post.setEnrolledParticipants(Set.of(new User()));
        post.setPostedByUsername(new User());
        return post;
    }
}
