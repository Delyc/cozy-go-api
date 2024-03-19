package com.cozyapp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozyapp.backend.dto.AvailabilityDto;
import com.cozyapp.backend.entity.Availability;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.repository.AvailabilityRepo;
import com.cozyapp.backend.repository.OurUserRepo;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepo availabilityRepo;

    @Autowired
    private OurUserRepo ourUserRepo;

    public Availability addAvailability(AvailabilityDto availabilityDto, Integer userId) {
        OurUsers user = ourUserRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found")); // Customize exception as needed

        Availability availability = new Availability();
        availability.setStartTime(availabilityDto.getStartTime());
        availability.setEndTime(availabilityDto.getEndTime());
        availability.setStatus(availabilityDto.getStatus());
        availability.setUser(user);

        return availabilityRepo.save(availability);
    }
    
    
}
