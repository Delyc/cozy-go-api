package com.cozyapp.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AvailabilityDto {
 private String day;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Integer userId;
    
    
}
