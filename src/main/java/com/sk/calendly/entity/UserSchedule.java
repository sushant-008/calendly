/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.entity;

import com.sk.calendly.dto.DayOfWeek;
import com.sk.calendly.dto.Interval;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Sushant
 */

@Entity
@Table(name="user_schedule")
public class UserSchedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="user_id")
    private int userId;
    @Column(name="day_name")
    private  DayOfWeek dayName;
    @Column(name="from_hour")
    private int fromHour;
    @Column(name="to_hour")
    private int toHour;
    @Column(name="from_minutes")
    private int fromMinutes;
    @Column(name="to_minutes")
    private int toMinutes;

    public UserSchedule(DayOfWeek dayName, int fromHour, int toHour, int fromMinutes, int toMinutes) {
        this.dayName = dayName;
        this.fromHour = fromHour;
        this.toHour = toHour;
        this.fromMinutes = fromMinutes;
        this.toMinutes = toMinutes;
    }

    public UserSchedule() {
    }

    public Interval getInterval() {
        return new Interval(fromHour, fromMinutes, toHour, toMinutes);
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DayOfWeek getDayName() {
        return dayName;
    }

    public void setDayName(DayOfWeek dayName) {
        this.dayName = dayName;
    }

    public int getFromHour() {
        return fromHour;
    }

    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }

    public int getToHour() {
        return toHour;
    }

    public void setToHour(int toHour) {
        this.toHour = toHour;
    }

    public int getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes(int fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public int getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(int toMinutes) {
        this.toMinutes = toMinutes;
    }
    
    
}
