/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

/**
 *
 * @author sushant.kumar
 */
public class CommonScheduleResponse extends PostResponse{
    private Interval commonInterval;

    public Interval getCommonInterval() {
        return commonInterval;
    }

    public void setCommonInterval(Interval commonInterval) {
        this.commonInterval = commonInterval;
    }
    
    
}
