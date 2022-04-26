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
    List<DiyWorkshop> getAllWorkshops();

    @Query("SELECT w FROM DiyWorkshop w WHERE w.confirmation = true")
    List<DiyWorkshop> getAllConfirmedWorkshops();

    @Query(nativeQuery = true, value = "SELECT * FROM workshops AS w WHERE w.confirmation = true ORDER BY w.date ASC LIMIT 3")
    List<DiyWorkshop> getThreeLastWorkshops();

    @Query(value = "SELECT w FROM DiyWorkshop  w WHERE w.diyUser = :diyUser")
    List<DiyWorkshop> getDiyWorkshopByDiyUserId(@Param("diyUser") DiyUser diyUser);

    @Query(nativeQuery = true, value = "SELECT * FROM workshops AS w JOIN workshops_reservation_user AS wru ON wru.diy_workshop_id = w.id WHERE wru.reservation_user_id = :diyUserId ")
    List<DiyWorkshop> getDiyWorkshopByReservationUser(@Param("diyUserId") Long diyUserId);

    @Query(nativeQuery = true, value = "SELECT * FROM workshops AS w " +
            "JOIN workshops_reservation_user AS wru ON w.id = wru.diy_workshop_id " +
            "JOIN users AS u ON wru.reservation_user_id = u.id " +
            "WHERE wru.reservation_user_id = :id AND w.date < CURRENT_DATE")
    List<DiyWorkshop> getBadgesByReservations(@Param("id") Long id);

}
