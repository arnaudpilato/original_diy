package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<DiyWorkshop, Long> {
    @Query("SELECT w FROM DiyWorkshop AS w")
    public List<DiyWorkshop> getAllWorkshops();

    @Query("SELECT w FROM DiyWorkshop AS w WHERE w.confirmation = true")
    public List<DiyWorkshop> getAllConfirmedWorkshops();
}
