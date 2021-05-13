package com.travelmate.repository;

import com.travelmate.model.Post;
import com.travelmate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostQueryRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    Set<Post> findAllByAuthorIn(List<User> users);

    Set<Post> findAllByActiveIsTrue();
}
