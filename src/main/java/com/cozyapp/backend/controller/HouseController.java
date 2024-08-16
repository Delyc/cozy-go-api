package com.cozyapp.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozyapp.backend.dto.HouseDto;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.service.AuthService;
import com.cozyapp.backend.service.HouseService;
import com.cozyapp.backend.service.RecommendationService;
import com.cozyapp.backend.service.WishlistService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private final WishlistService wishlistService;

    public HouseController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/agent/addHouse/{userId}")
    public ResponseEntity<House> addHouse(@PathVariable Integer userId, @RequestBody HouseDto houseDto ) {
        House newHouse = houseService.addHouse(houseDto, userId);
        return new ResponseEntity<>(newHouse, HttpStatus.CREATED);
    }

    @DeleteMapping("agent/deleteHouse/{id}")
    public ResponseEntity<Void> deleteHouseById(@PathVariable Integer id) {
        houseService.deleteHouseById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/public/houses")
    public List<House> getAllHouses() {
        return houseService.getAllHouses();
    }
    @PostMapping("/user/toggle/{user_id}/addh/{house_id}")
    public String toggleWishlistItem(@PathVariable Integer user_id, @PathVariable Integer house_id) {
        wishlistService.addHouseToWishlist(user_id, house_id);
        return "House added to the wishlist successfully!";
    }

    @GetMapping("/public/houses/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable Integer id) {
        return houseService.getHouseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/public/houses/{id}/recommendations")
    public ResponseEntity<List<House>> getHouseRecommendationById(@PathVariable Integer id, @RequestParam(defaultValue = "3") int topN) {
        return new ResponseEntity<>(recommendationService.recommendSimilarHouses(id, topN), HttpStatus.OK);
    }

     @PatchMapping("/agent/updateHouse/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable Integer id, @RequestBody House houseDetails) {
        return houseService.updateHouse(id, houseDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/public/share/{id}")
    public ResponseEntity<?> generateShareLink(@PathVariable Integer id) {
        return houseService.generateShareLink(id)
                .map(link -> ResponseEntity.ok().body(Map.of("shareLink", link)))
                .orElse(ResponseEntity.notFound().build());
    }


    
}
