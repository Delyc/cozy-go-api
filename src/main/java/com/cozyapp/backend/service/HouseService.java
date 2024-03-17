package com.cozyapp.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    
    public List<House> getAllHouses() {
        return houseRepo.findAll();
    }

    public Optional<House> getHouseById(Integer id) {
        return houseRepo.findById(id);
    }

    public void deleteHouseById(Integer id) {
        houseRepo.deleteById(id);
    }

    public Optional<House> updateHouse(Integer id, House houseDetails) {
        return houseRepo.findById(id).map(existingHouse -> {
            if (houseDetails.getTitle() != null) {
                existingHouse.setTitle(houseDetails.getTitle());
            }
            if (houseDetails.getUser() != null) {
                existingHouse.setUser(houseDetails.getUser());
            }

            return houseRepo.save(existingHouse);
        });
    }
}
