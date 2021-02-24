package com.travelmate.utils;

import com.travelmate.model.Post;
import com.travelmate.repository.PostCommandRepository;
import com.travelmate.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdvertisementScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementScheduler.class);

    private final PostQueryRepository postQueryRepository;
    private final PostCommandRepository postCommandRepository;

    //    @Scheduled(fixedRateString = "86400000", initialDelay = 3000)
    @Scheduled(cron = "0 12 * * ?")
    public void checkIfPostsAreOutOfDate() {
        Set<Post> posts = postQueryRepository.findAllByActiveIsTrue();
        Set<Post> outOfDatePosts = new HashSet<>();
        posts.stream().filter(post -> post.getDateTo().isBefore(LocalDate.now())).forEach(post -> {
            post.setPostToInactive();
            outOfDatePosts.add(post);
        });
        postCommandRepository.saveAll(outOfDatePosts);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("There were %d outdated posts.", outOfDatePosts.size()));
        }
    }
}
