package com.cozyapp.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozyapp.backend.dto.WishlistItemDTO;
import com.cozyapp.backend.service.WishlistService;

@RestController
@RequestMapping
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/user/toggle/{userId}/{houseId}")
    public ResponseEntity<?> toggleWishlistItem(@PathVariable Integer userId, @PathVariable Integer houseId) {
        WishlistItemDTO wishlistItemDTO = wishlistService.toggleWishlistItem(userId, houseId);

        if (wishlistItemDTO != null) {
            // If the item was added to the wishlist, return the DTO
            return new ResponseEntity<>(wishlistItemDTO, HttpStatus.CREATED);
        } else {
            // If the item was removed from the wishlist, return an appropriate message or status
            return ResponseEntity.ok().body("House removed from wishlist");
        }
    }

    @GetMapping("/user/wishlist/{userId}")
    public ResponseEntity<List<WishlistItemDTO>> getUserWishlist(@PathVariable Integer userId) {
        List<WishlistItemDTO> wishlistItems = wishlistService.getUserWishlist(userId);
        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
    }
}
