package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<DiyBadge, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM badges AS b WHERE b.name LIKE CONCAT('%',:searchBadge,'%')")
    List<DiyBadge> getBadgesByFilter(@Param("searchBadge") String searchBadge);
}
