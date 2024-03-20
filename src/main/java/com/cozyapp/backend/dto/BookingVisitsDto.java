package com.cozyapp.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingVisitsDto {
    private Integer id;
    private String message;
    private String bookingStatus;
    private Integer userId;
    private Integer availabilityId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
