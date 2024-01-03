/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

import com.sk.calendly.entity.UserEvent;

/**
 *
 * @author Sushant
 */
public class CreateEventResponse extends PostResponse{
    private UserEvent userEvent;
    
    public UserEvent getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(UserEvent userEvent) {
        this.userEvent = userEvent;
    }

    
}
