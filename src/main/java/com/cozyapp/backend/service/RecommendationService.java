package com.cozyapp.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.repository.HouseRepo;

@Service
public class RecommendationService {

    private final HouseService houseService;

    public RecommendationService(HouseService houseService) {
        this.houseService = houseService;
    }

    public List<House> recommendSimilarHouses(Integer houseId, int topN) {
        House targetHouse = houseService.getHouseById(houseId)
                .orElseThrow(() -> new IllegalArgumentException("House not found"));

        List<House> allHouses = houseService.getAllHouses();

        // Remove the target house from the list
        allHouses = allHouses.stream()
                .filter(house -> !house.getId().equals(houseId))
                .collect(Collectors.toList());

        // Calculate similarity and sort
        return allHouses.stream()
                .sorted((house1, house2) -> {
                    double similarity1 = calculateSimilarity(targetHouse, house1);
                    double similarity2 = calculateSimilarity(targetHouse, house2);
                    return Double.compare(similarity2, similarity1);
                })
                .limit(topN)
                .collect(Collectors.toList());
    }

    private double calculateSimilarity(House house1, House house2) {
        // Combining features: Description + Number of rooms
        String features1 = house1.getDescription() + " " + house1.getArea() + " " + house1.getPrice() + " " + house1.getTitle();
        String features2 = house2.getDescription() + " " + house2.getArea() + " " + house2.getPrice() + " " + house2.getTitle();

        return SimilarityUtils.computeCosineSimilarity(features1, features2);
    }
}