package com.cozyapp.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cozyapp.backend.dto.HouseDto;
import com.cozyapp.backend.dto.WishlistItemDTO;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.entity.Wishlist;
import com.cozyapp.backend.entity.WishlistItem;
import com.cozyapp.backend.entity.WishlistItemId;
import com.cozyapp.backend.repository.HouseRepo;
import com.cozyapp.backend.repository.OurUserRepo;
import com.cozyapp.backend.repository.WishlistItemRepo;
import com.cozyapp.backend.repository.WishlistRepository;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional
public class WishlistService {

    @Autowired
    private WishlistItemRepo wishlistItemRepo;

    @Autowired
    private HouseRepo houseRepo;

    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired

    private WishlistRepository wishlistRepository;




 public void addHouseToWishlist(Integer user_id, Integer house_id) {
        OurUsers user = ourUserRepo.findById(user_id).orElse(null);
        House house = houseRepo.findById(house_id).orElse(null);

        if (user != null && house != null) {
            Wishlist existingWishlistItem = wishlistRepository.findByUserAndHouse(user, house);
            if (existingWishlistItem != null) {
                // Item already exists, remove it
                wishlistRepository.delete(existingWishlistItem);
            } else {
                // Item doesn't exist, add it
                Wishlist wishlist = new Wishlist();
                wishlist.setUser(user);
                wishlist.setHouse(house);
                wishlistRepository.save(wishlist);
            }
        }
    }

    public List<WishlistResponse> getUserWishlistWithHouseDetails(Integer user_id) {
        Optional<OurUsers> userOptional = ourUserRepo.findById(user_id);
    
        if (userOptional.isPresent()) {
            OurUsers user = userOptional.get();
            List<WishlistResponse> wishlistResponses = new ArrayList<>();
    
            for (Wishlist wishlist : user.getWishlists()) {
                WishlistResponse response = new WishlistResponse();
                response.setId(wishlist.getId());
                House house = wishlist.getHouse();
                HouseDto houseResponse = new HouseDto();
                // houseResponse.setId(house.getId());
                houseResponse.setTitle(house.getTitle());
                houseResponse.setPrice(house.getPrice());
                houseResponse.setCoverImageUrl(house.getCoverImageUrl());
                houseResponse.setDescription(house.getDescription());
                houseResponse.setLongi(house.getLongi());
                houseResponse.setLat(house.getLat());
                houseResponse.setStreetNumber(house.getStreetNumber());
                // houseResponse.setGoogleMapLocation(house.getGoogleMapLocation());
    
                // Retrieve agent information
                OurUsers agent = house.getUser(); // Use the 'getAgent' method
                if (agent != null) {
                    // Set agent-related fields in the response
                    // houseResponse.setAgentId(agent.getId());
                    // houseResponse.setAgentEmail(agent.getEmail());
                    // houseResponse.setAgentPhoneNumber(agent.getPhone());
                    houseResponse.setFullname(agent.getFullname()); // Adjust this based on your User entity

                }
    
                response.setHouse(house);
                wishlistResponses.add(response);
            }
            return wishlistResponses;
        } else {
            return Collections.emptyList();
        }
    }














    public WishlistItemDTO toggleWishlistItem(Integer userId, Integer houseId) {
        WishlistItemId wishlistItemId = new WishlistItemId(userId, houseId);
        Optional<WishlistItem> wishlistItemOptional = wishlistItemRepo.findById(wishlistItemId);

        if (wishlistItemOptional.isPresent()) {
            // If exists, remove it
            wishlistItemRepo.deleteById(wishlistItemId);
            return null;
        } else {
            // If not exists, add it
            OurUsers user = ourUserRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            House house = houseRepo.findById(houseId)
                    .orElseThrow(() -> new RuntimeException("House not found"));

            WishlistItem wishlistItem = new WishlistItem();
            wishlistItem.setId(wishlistItemId);
            wishlistItem.setUser(user);
            wishlistItem.setHouse(house);
            WishlistItem savedItem = wishlistItemRepo.save(wishlistItem);
            WishlistItemDTO wishlistItemDTO = new WishlistItemDTO();
            wishlistItemDTO.setUserId(userId);
            wishlistItemDTO.setHouseId(houseId);
            wishlistItemDTO.setAddedAt(savedItem.getAddedAt());
            wishlistItemDTO.setHouseTitle(house.getTitle());

            return wishlistItemDTO;
        }
    }

    @Transactional(readOnly = true)
    public List<WishlistItemDTO> getUserWishlist(Integer userId) {
        List<WishlistItem> wishlistItems = wishlistItemRepo.findAllByUserId(userId);
  
        return wishlistItems.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private WishlistItemDTO convertToDto(WishlistItem wishlistItem) {
        WishlistItemDTO dto = new WishlistItemDTO();
        dto.setUserId(wishlistItem.getUser().getId());
        dto.setHouseId(wishlistItem.getHouse().getId());
        dto.setAddedAt(wishlistItem.getAddedAt());
        dto.setHouseTitle(wishlistItem.getHouse().getTitle());
        return dto;
    }

    public Optional<String> generateWishlistShareLink(Integer user_id) {
        return wishlistItemRepo.findAllByUserId(user_id)
                .stream()
                .map(property -> "https://capstoneapi-production-b1ec.up.railway.app/public/wishlist/" + property.getUser().getId())
                .findFirst();
    }

    public List<WishlistItemDTO> getWishlistByShareId(String shareId) {
        Integer userId = Integer.parseInt(shareId);
        List<WishlistItem> wishlistItems = wishlistItemRepo.findAllByUserId(userId);
        return wishlistItems.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}