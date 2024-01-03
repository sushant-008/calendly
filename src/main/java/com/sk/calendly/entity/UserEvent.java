/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "user_event")
public class UserEvent implements Comparable<UserEvent>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    @Column(name = "date_num")
    private int eventDate;
    private int fromHour;
    private int fromMinutes;
    private int toHour;
    private int toMinutes;
    @Column(name = "event_desc")
    private String eventDescription;
    
    @JsonIgnore
    public Interval getInterval() {
        return new Interval(fromHour, fromMinutes, toHour, toMinutes);
    }

    public UserEvent() {
    }

    public UserEvent(int userId, int eventDate, int fromHour, int fromMinutes, int toHour, int toMinutes, String eventDescription) {
        this.userId = userId;
        this.eventDate = eventDate;
        this.fromHour = fromHour;
        this.fromMinutes = fromMinutes;
        this.toHour = toHour;
        this.toMinutes = toMinutes;
        this.eventDescription = eventDescription;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventDate() {
        return eventDate;
    }

    public void setEventDate(int eventDate) {
        this.eventDate = eventDate;
    }

    public int getFromHour() {
        return fromHour;
    }

    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }

    public int getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes(int fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public int getToHour() {
        return toHour;
    }

    public void setToHour(int toHour) {
        this.toHour = toHour;
    }

    public int getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(int toMinutes) {
        this.toMinutes = toMinutes;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public int compareTo(UserEvent o) {
        return this.getInterval().compareTo(o.getInterval());
    }
    
    
}
