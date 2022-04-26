package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<DiyCategory, Long> {
}
