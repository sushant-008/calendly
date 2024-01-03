/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sushant
 */
public class Interval implements Comparable<Interval>{
    private int fromHour;
    private int fromMinutes;
    private int toHour;
    private int toMinutes;
    
    
    private final int leftMinutes;
    private final int rightMinutes;

    public Interval() {
        leftMinutes = 0;
        rightMinutes = 0;
    }

    
    
    
    public Interval(int fromHour, int fromMinutes, int toHour, int toMinutes) {
        this.fromHour = fromHour;
        this.fromMinutes = fromMinutes;
        this.toHour = toHour;
        this.toMinutes = toMinutes;
        
        leftMinutes = fromHour*60+fromMinutes;
        rightMinutes = toHour*60+toMinutes;
    }

    @JsonIgnore
    public int getLeftMinutes() {
        return fromHour*60+fromMinutes;
    }

    @JsonIgnore
    public int getRightMinutes() {
        return toHour*60+toMinutes;
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
    
    public static Interval getDefault() {
        return new Interval(8,0,5,0);
    }

    @Override
    public String toString() {
        return "Interval{" + "fromHour=" + fromHour + ", fromMinutes=" + fromMinutes + ", toHour=" + toHour + ", toMinutes=" + toMinutes + '}';
    }
    
    
    @JsonIgnore
    public boolean isValid() {
        if(fromHour<0 || fromHour>23 || fromMinutes<0 || fromMinutes>59 || toHour<0 || toHour>23 || toMinutes<0 || toMinutes>59) return false;
        return getLeftMinutes()<getRightMinutes();
    }

    @Override
    public int compareTo(Interval o) {
        if(this.fromHour==o.fromHour) {
            return Integer.compare(this.fromMinutes, o.fromMinutes);
        } else {
            return Integer.compare(this.fromHour, o.fromHour);
        }
    }
    
    public boolean isOverlapping(Interval interval) {
        if(interval.getLeftMinutes()> this.getRightMinutes()) return false;
        if(interval.getRightMinutes()>this.getLeftMinutes()) return true;
        return interval.getLeftMinutes()>this.getLeftMinutes() && interval.getRightMinutes()<this.getRightMinutes();
        
    }
    
    static int dateToDay(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(10,30,11,20));
        list.add(new Interval(10,20,10,30));
        list.add(new Interval(9,30,10,30));
        list.add(new Interval(13,0,13,30));
        Collections.sort(list);
//        for(Interval inv : list) {
//            System.out.println(inv.toString());
//        }
        
        Interval t1 = new Interval(14,0, 17, 0);
        Interval t2 = new Interval(14,30, 14, 0);
        
        boolean overlap = t1.isOverlapping(t2);
        
        System.out.println(t1.isValid());
        System.out.println(t2.isValid());
        //System.out.println("Overlap ? "+overlap);
        int x = dateToDay(1704133800);
        System.out.println(x);
        
    }
    
    
}
