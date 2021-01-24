package com.travelmate.repository;

import com.travelmate.model.Post;
import com.travelmate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    Optional<List<Post>> findAllByPostedByUsernameIn(List<User> users);

    Optional<List<Post>> findAllByIsActualIsTrue();
}
