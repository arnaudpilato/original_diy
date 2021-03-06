package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Optional<DiyUser> findByUsername(String username);

    @Query("SELECT u FROM DiyUser u ORDER BY u.username ASC")
    Page<DiyUser> findAllByOrderByIdDesc(Pageable pageable);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("SELECT u FROM DiyUser u WHERE u.username = :username")
    DiyUser getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM DiyUser u WHERE u.resetPasswordToken = :token")
    DiyUser findByResetPasswordToken(@Param("token") String token);

    @Query("SELECT u FROM DiyUser u WHERE u.email = :email")
    DiyUser findByEmail(@Param("email") String email);

}
