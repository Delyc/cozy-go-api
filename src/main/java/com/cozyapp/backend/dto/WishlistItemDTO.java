package com.cozyapp.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WishlistItemDTO {
    private Integer userId;
    private Integer houseId;
    private String houseTitle; 
    private String ownerName; 
    private LocalDateTime addedAt;

}
