package com.cozyapp.backend.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozyapp.backend.dto.AvailabilityDto;
import com.cozyapp.backend.entity.Availability;
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

    
}
