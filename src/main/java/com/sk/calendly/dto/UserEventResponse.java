/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

import com.sk.calendly.entity.UserEvent;
import java.util.List;

/**
 *
 * @author Sushant
 */
public class UserEventResponse {
    private List<UserEvent> events;

    public List<UserEvent> getEvents() {
        return events;
    }

    public void setEvents(List<UserEvent> events) {
        this.events = events;
    }
    
    
}
