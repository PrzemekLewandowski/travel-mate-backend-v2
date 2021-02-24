package com.travelmate.repository;

import com.travelmate.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommandRepository extends JpaRepository<Post,Long> {
}
