/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sk.calendly.client.internal;

import org.springframework.http.HttpStatusCode;

/**
 *
 * @author sushant.kumar
 */
class WebResponse {
        private HttpStatusCode statusCode;
        private String response;

        public void setStatusCode(HttpStatusCode statusCode) {
            this.statusCode = statusCode;
        }

        public void setResponse(String response) {
            this.response = response;
        }
        
        

        public WebResponse(HttpStatusCode statusCode, String response) {
            this.statusCode = statusCode;
            this.response = response;
        }

        public HttpStatusCode getStatusCode() {
            return statusCode;
        }

        public String getResponse() {
            return response;
        }
        
        
        
        
        
        
    }
