package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<DiyComment, Long> {

@Query( value = "SELECT w FROM DiyComment  w WHERE w.diyWorkshop = :diyWorkshop")
    List<DiyComment> getCommentByWorkshop(@Param("diyWorkshop") DiyWorkshop diyWorkshop);

}
