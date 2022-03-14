package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<DiyBadge, Long> {
}
