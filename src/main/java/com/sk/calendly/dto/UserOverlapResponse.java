/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.dto;

import java.util.List;

/**
 *
 * @author Sushant
 */
public class UserOverlapResponse {
    private List<OverlapResponse> overlaps;

    public List<OverlapResponse> getOverlaps() {
        return overlaps;
    }

    public void setOverlaps(List<OverlapResponse> overlaps) {
        this.overlaps = overlaps;
    }
    
    
}
