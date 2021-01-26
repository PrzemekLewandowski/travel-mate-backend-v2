package com.travelmate.repository;

import com.travelmate.model.Post;
import com.travelmate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    List<Post> findAllByPostedByUsernameIn(List<User> users);

    List<Post> findAllByIsActualIsTrue();
}
