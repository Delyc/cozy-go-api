package com.cozyapp.backend.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cozyapp.backend.service.WishlistResponse;

import lombok.Data;

@Data
public class HouseDto {
        private String title;
        private String fullname;
        private Integer userId;
        private String description;
        private String coverImageUrl;
        private String price;
        private String lat;
        private String longi;
        private String streetNumber;
        private Long bedRooms;
    private String area;
        private Long bathRooms;
        private String typeOfHouse;
        private String district;
    private String sector;
        private List<String> pictureUrls;
        private List<String> videoUrls;
        private String availableStatus;
        private Map<String, Boolean> features = new HashMap<>();
        private LocalDateTime createdAt;
         private List<WishlistResponse> wishlists;
        private LocalDateTime updatedAt;

}
