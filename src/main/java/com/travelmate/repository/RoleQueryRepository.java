package com.travelmate.repository;


import com.travelmate.model.Role;
import com.travelmate.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleQueryRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
