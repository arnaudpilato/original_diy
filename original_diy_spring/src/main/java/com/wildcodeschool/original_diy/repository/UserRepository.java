package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Pil : Added the possibility to search by username <br>
 * - Added boolean to check if username and email exist
 */
@Repository
public interface UserRepository extends JpaRepository<DiyUser, Long> {
    @Query("SELECT u FROM DiyUser AS u WHERE u.username = :username")
    public DiyUser getByUsername(@Param("username") String username);

    @Query("SELECT u FROM DiyUser AS u")
    public List<DiyUser> getAllUsers();

    Optional<DiyUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
