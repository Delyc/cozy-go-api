package com.cozyapp.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozyapp.backend.dto.AvailabilityDto;
import com.cozyapp.backend.entity.Availability;
import com.cozyapp.backend.entity.House;
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

    public List<Availability> getAllAvailabilities() {
        return availabilityRepo.findAll();
    }

    public Optional<Availability> getHAvailabilityById(Integer id) {
        return availabilityRepo.findById(id);
    }

    public void deleteAvailabilityById(Integer id) {
        availabilityRepo.deleteById(id);
    }


    public Optional<Availability> updateAvailability(Integer id, Availability availabilityDetails) {
        return availabilityRepo.findById(id).map(existingAvailability -> {
            if (availabilityDetails.getEndTime() != null) {
                existingAvailability.setEndTime(availabilityDetails.getEndTime());
            }
            if (availabilityDetails.getStartTime() != null) {
                existingAvailability.setStartTime(availabilityDetails.getStartTime());
            }

            if(availabilityDetails.getStatus() != null) {
                existingAvailability.setStatus(availabilityDetails.getStatus());
            }

            return availabilityRepo.save(existingAvailability);
        });
    }

}
