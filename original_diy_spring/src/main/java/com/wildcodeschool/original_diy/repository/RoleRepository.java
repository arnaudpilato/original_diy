package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyRole;
import com.wildcodeschool.original_diy.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Pil : Added the possibility to search an eRole by name
 */
@Repository
public interface RoleRepository extends JpaRepository<DiyRole, Long> {
    Optional<DiyRole> findByName(ERole name);
}
