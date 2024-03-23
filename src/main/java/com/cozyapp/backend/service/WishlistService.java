package com.cozyapp.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cozyapp.backend.dto.WishlistItemDTO;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.entity.WishlistItem;
import com.cozyapp.backend.entity.WishlistItemId;
import com.cozyapp.backend.repository.HouseRepo;
import com.cozyapp.backend.repository.OurUserRepo;
import com.cozyapp.backend.repository.WishlistItemRepo;

@Service
public class WishlistService {

    @Autowired
    private WishlistItemRepo wishlistItemRepo;

    @Autowired
    private HouseRepo houseRepo;

    @Autowired
    private OurUserRepo ourUserRepo;

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

    public Optional<String> generateWishlistShareLink(Integer userId) {
        return wishlistItemRepo.findAllByUserId(userId)
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
