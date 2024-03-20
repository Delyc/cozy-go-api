package com.cozyapp.backend.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozyapp.backend.dto.AvailabilityDto;
import com.cozyapp.backend.entity.Availability;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.service.AvailabilityService;

@RestController
@RequestMapping
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping("/agent/addAvailability/{userId}")
    public ResponseEntity<Availability> addAvailability(@PathVariable Integer userId, @RequestBody AvailabilityDto availabilityDto) {
        Availability newAvailability = availabilityService.addAvailability(availabilityDto, userId);
        return new ResponseEntity<>(newAvailability, HttpStatus.CREATED);
    }

     @DeleteMapping("agent/deleteAvailability/{id}")
    public ResponseEntity<Void> deleteHouseById(@PathVariable Integer id) {
        availabilityService.deleteAvailabilityById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/public/allAvailabilities")
    public List<Availability> getAllHouses() {
        return availabilityService.getAllAvailabilities();
    }

    @GetMapping("/public/availability/{id}")
    public ResponseEntity<Availability> getHouseById(@PathVariable Integer id) {
        return availabilityService.getHAvailabilityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

     @PatchMapping("/agent/updateAvailability/{id}")
    public ResponseEntity<Availability> updateAvailability(@PathVariable Integer id, @RequestBody Availability availabilityDetails) {
        return availabilityService.updateAvailability(id, availabilityDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
