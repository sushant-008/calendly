/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.repository;

import com.sk.calendly.dto.DayOfWeek;
import com.sk.calendly.entity.UserSchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sushant.kumar
 */

@Repository
public interface ScheduleRepository extends JpaRepository<UserSchedule, Long> {
    
    
    @Query(value = "select * from user_schedule where user_id=?1",nativeQuery = true)
    List<UserSchedule> getByUserId(long userId);
    
    @Query(value = "select * from user_schedule where user_id=?1 and day_name=?2",nativeQuery = true)
    UserSchedule getByUserId(long userId, DayOfWeek date);
}
