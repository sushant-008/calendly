/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

/**
 *
 * @author Sushant
 */
public class OverlapRequestParams {
    private int userOne;
    private int userTwo;
    private int day;

    public OverlapRequestParams() {
    }

    public OverlapRequestParams(int userOne, int userTwo, int day) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.day = day;
    }

    
    
    public int getUserOne() {
        return userOne;
    }

    public void setUserOne(int userOne) {
        this.userOne = userOne;
    }

    public int getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(int userTwo) {
        this.userTwo = userTwo;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    
}
