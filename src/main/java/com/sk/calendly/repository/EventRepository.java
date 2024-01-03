/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.repository;

import com.sk.calendly.entity.UserEvent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sushant.kumar
 */

@Repository
public interface EventRepository extends JpaRepository<UserEvent, Long> {
    
    
    @Query(value = "select * from user_event where user_id=?1 and date_num>=?2",nativeQuery = true)
    List<UserEvent> getByUserId(long userId, int date);
}
