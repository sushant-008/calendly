/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sushant
 */
public class Schedule implements Comparable<Schedule>{
    private final DayOfWeek dayName;
    private final Interval interval;

    public Schedule(DayOfWeek dayName, Interval interval) {
        this.dayName = dayName;
        this.interval = interval;
    }

    public Schedule() {
        this.dayName = DayOfWeek.INVALID;
        this.interval = new Interval(0, 0, 0, 0);
    }
    
    

    public DayOfWeek getDayName() {
        return dayName;
    }

    public Interval getInterval() {
        return interval;
    }

    @Override
    public int compareTo(Schedule o) {
        return Integer.compare(this.dayName.ordinal(), o.dayName.ordinal());
    }

    @Override
    public String toString() {
        return "Schedule{" + "dayName=" + dayName + ", interval=" + interval + '}';
    }
    
    public boolean validate() {
        
        return interval.isValid() && interval.getRightMinutes() >= interval.getLeftMinutes();
    }
    
    
    
    public static void main(String[] args) {
        List<Schedule> list = new ArrayList<>();
        list.add(new Schedule(DayOfWeek.MONDAY, Interval.getDefault()));
        list.add(new Schedule(DayOfWeek.THURSDAY, Interval.getDefault()));
        list.add(new Schedule(DayOfWeek.WEDNESDAY, Interval.getDefault()));
        list.add(new Schedule(DayOfWeek.TUESDAY, Interval.getDefault()));
        list.add(new Schedule(DayOfWeek.FRIDAY, Interval.getDefault()));
        
        Collections.sort(list);
        for(Schedule s : list) {
            System.out.println(s);
        }
    }
    
    
}
