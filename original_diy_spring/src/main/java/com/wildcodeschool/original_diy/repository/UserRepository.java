package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<DiyUser, Long> {
    @Query("SELECT u FROM DiyUser AS u WHERE u.username = :username")
    public DiyUser getByUsername(@Param("username") String username);

    @Query("SELECT u FROM DiyUser AS u")
    public List<DiyUser> getAllUsers();

}
