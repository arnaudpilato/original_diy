package com.wildcodeschool.original_diy.repository;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<DiyWorkshop, Long> {
    @Query("SELECT w FROM DiyWorkshop w")
    public List<DiyWorkshop> getAllWorkshops();

    @Query("SELECT w FROM DiyWorkshop w WHERE w.confirmation = true")
    public List<DiyWorkshop> getAllConfirmedWorkshops();

    @Query(nativeQuery = true, value = "SELECT * FROM workshops AS w WHERE w.confirmation = true ORDER BY w.date DESC LIMIT 3")
    public List<DiyWorkshop> getThreeLastWorkshops();

    @Query(value = "SELECT w FROM DiyWorkshop  w WHERE w.diyUser = :diyUser")
    public List<DiyWorkshop> getDiyWorkshopByDiyUserId(@Param("diyUser") DiyUser diyUser);

    @Query(nativeQuery = true, value = "SELECT * FROM workshops AS w JOIN workshops_reservation_user AS wru ON wru.diy_workshop_id = w.id WHERE wru.reservation_user_id = :diyUserId ")
    public List<DiyWorkshop> getDiyWorkshopByReservationUser(@Param("diyUserId") Long diyUserId);

}
