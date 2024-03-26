package com.cozyapp.backend.dto;

import java.time.LocalDateTime;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.entity.OurUsers;
import lombok.Data;

@Data
public class WishlistItemDTO {
    private Integer userId;
    private Integer houseId;
    private String houseTitle;
    private String ownerName;
    private HouseDto house; // This is already present
    private OurUsers user; // Added field for the whole user object
    private LocalDateTime addedAt;
}
