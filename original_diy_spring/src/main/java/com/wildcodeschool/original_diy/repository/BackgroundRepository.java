package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyBackground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BackgroundRepository extends JpaRepository<DiyBackground, Long> {
    Optional<DiyBackground> findByname(String name);
    @Query("SELECT b FROM DiyBackground AS b WHERE b.visible = false")
    List<DiyBackground> getAllBackgroundWithoutVisibility();

    @Query("SELECT b FROM DiyBackground AS b WHERE b.visible = true")
    List<DiyBackground> getAllBackgroundWithVisibility();
}
