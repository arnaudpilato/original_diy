package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<DiyWorkshop, Long> {
}
