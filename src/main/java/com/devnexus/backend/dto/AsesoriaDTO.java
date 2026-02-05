package com.devnexus.backend.dto;

import lombok.Data;

@Data
public class AsesoriaDTO {
    private String id;             
    private String programmerId;  
    private String programmerName; 
    
    private String clientName;
    private String clientEmail;   
    
    private String date;
    private String time;
    private String topic;          
    private String status;        
    private String responseMessage; 
}