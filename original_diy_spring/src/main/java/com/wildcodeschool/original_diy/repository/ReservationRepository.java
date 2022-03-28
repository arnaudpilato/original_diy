package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import com.wildcodeschool.original_diy.entity.DiyReservation;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<DiyReservation, Long> {


    @Query( value = "SELECT w FROM DiyReservation  w WHERE w.user = :user")
    public List<DiyWorkshop> getReservationByUser(@Param("user") DiyUser user);

    @Query( value = "SELECT w FROM DiyReservation  w WHERE w.workshop = :workshop")
    public List<DiyReservation> getReservationByWorkshop(@Param("workshop") DiyWorkshop workshop);

}
