package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterRepository extends JpaRepository<DiyFooter, Long> {
}
