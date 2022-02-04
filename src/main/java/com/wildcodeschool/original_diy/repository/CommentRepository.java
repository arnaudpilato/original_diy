package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<DiyComment, Long> {


}
