/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Sushant
 */
public class Slot implements Comparable<Slot>{
    private  Interval interval;
    private  SlotType slotType;
    private  String description;

    public Slot() {
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public Slot(Interval interval, SlotType slotType, String description) {
        this.interval = interval;
        this.slotType = slotType;
        this.description = description;
    }

    public Interval getInterval() {
        return interval;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Slot o) {
        return this.getInterval().compareTo(o.getInterval());
    }
    
    @JsonIgnore
    public boolean isEmpty() {
        Interval i = getInterval();
        return i.getFromHour()==i.getToHour() && i.getFromMinutes()==i.getToMinutes();
    }
    
    
}
