package com.cozyapp.backend.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class HouseDto {
        private String title;
        private Integer userId;
    private LocalDateTime createdAt;

            private LocalDateTime updatedAt;

    }
    
