package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.entity.DiyWorkshopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopUserRepository extends JpaRepository<DiyWorkshopUser, Long> {
}
