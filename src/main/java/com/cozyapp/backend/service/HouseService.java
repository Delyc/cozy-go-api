package com.cozyapp.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cozyapp.backend.dto.HouseDto;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.entity.Picture;
import com.cozyapp.backend.entity.Video;
import com.cozyapp.backend.repository.HouseRepo;
import com.cozyapp.backend.repository.OurUserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HouseService {

    @Autowired
    private HouseRepo houseRepo;

    @Autowired
    OurUserRepo ourUserRepo;

    public House addHouse(HouseDto houseDTO, Integer userId) {
        OurUsers user = ourUserRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        House house = new House();
        house.setTitle(houseDTO.getTitle());
        house.setUser(user);
        house.setDescription(houseDTO.getDescription());
        house.setCoverImageUrl(houseDTO.getCoverImageUrl());
        house.setPrice(houseDTO.getPrice());
        house.setLat(houseDTO.getLat());
        house.setLongi(houseDTO.getLongi());
        house.setStreetNumber(houseDTO.getStreetNumber());
        house.setAvailableStatus(houseDTO.getAvailableStatus());
        house.setArea(houseDTO.getArea());
        house.setBathRooms(houseDTO.getBathRooms());
        house.setBedRooms(houseDTO.getBedRooms());
        house.setTypeOfHouse(houseDTO.getTypeOfHouse());
        List<Picture> pictures = houseDTO.getPictureUrls().stream()
                .map(url -> new Picture(null, house, url))
                .collect(Collectors.toList());
        house.setPictures(pictures);

        List<Video> videos = houseDTO.getVideoUrls().stream()
                .map(url -> new Video(null, house, url))
                .collect(Collectors.toList());
        house.setVideos(videos);
        house.setFeatures(houseDTO.getFeatures());

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

    public Optional<String> generateShareLink(Integer id) {
        return houseRepo.findById(id).map(
                property -> "http://localhost:3000/house/" + property.getId());
    }

}
