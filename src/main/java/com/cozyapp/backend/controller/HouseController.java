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

import com.cozyapp.backend.dto.HouseDto;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.service.HouseService;

@RestController
@RequestMapping
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("/agent/addHouse/{userId}")
    public ResponseEntity<House> addHouse(@PathVariable Integer userId, @RequestBody HouseDto houseDto ) {
        House newHouse = houseService.addHouse(houseDto, userId);
        return new ResponseEntity<>(newHouse, HttpStatus.CREATED);
    }
    
}
