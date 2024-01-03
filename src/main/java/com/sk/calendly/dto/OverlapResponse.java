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
public class OverlapResponse {
    private UserEvent firstEvent;
    private UserEvent secondEvent;

    public OverlapResponse() {
    }

    public OverlapResponse(UserEvent firstEvent, UserEvent secondEvent) {
        this.firstEvent = firstEvent;
        this.secondEvent = secondEvent;
    }

    
    
    public UserEvent getFirstEvent() {
        return firstEvent;
    }

    public void setFirstEvent(UserEvent firstEvent) {
        this.firstEvent = firstEvent;
    }

    public UserEvent getSecondEvent() {
        return secondEvent;
    }

    public void setSecondEvent(UserEvent secondEvent) {
        this.secondEvent = secondEvent;
    }
    
    
}
