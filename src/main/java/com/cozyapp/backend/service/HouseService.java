package com.cozyapp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozyapp.backend.dto.HouseDto;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.repository.HouseRepo;
import com.cozyapp.backend.repository.OurUserRepo;

@Service
public class HouseService {

    @Autowired
    private HouseRepo houseRepo;

    @Autowired
    OurUserRepo ourUserRepo;
    
    public House addHouse(HouseDto houseDTO, Integer userId) {
        OurUsers user = ourUserRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found")); // Customize exception as needed
    
        House house = new House();
        house.setTitle(houseDTO.getTitle());
        house.setUser(user);
        
        return houseRepo.save(house);
    }
    
}
