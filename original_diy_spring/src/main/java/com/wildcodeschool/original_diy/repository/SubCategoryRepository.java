package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiySubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<DiySubCategory, Integer> {
}
