/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.client.internal;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 *
 * @author sushant.kumar
 */
public class WebRequest<T> {
    private TypeReference<T> type;
    private String path;
    private Object payload;

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    
    
    
    public WebRequest(TypeReference<T> type, String path) {
        this.type = type;
        this.path = path;
    }
    
    

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public TypeReference<T> getType() {
        return type;
    }
    
    
    
}
