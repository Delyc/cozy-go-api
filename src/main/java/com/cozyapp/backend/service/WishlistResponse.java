package com.cozyapp.backend.service;



import com.cozyapp.backend.dto.HouseDto;
import com.cozyapp.backend.entity.House;

import lombok.Data;

@Data
public class WishlistResponse {

        private Integer id;
        private House house;
    
       
    
}