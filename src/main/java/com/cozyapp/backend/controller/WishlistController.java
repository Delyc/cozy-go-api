package com.cozyapp.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozyapp.backend.dto.WishlistItemDTO;
import com.cozyapp.backend.service.WishlistResponse;
import com.cozyapp.backend.service.WishlistService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")

public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/user/toggle/{user_id}/add/{house_id}")
    public String toggleWishlistItem(@PathVariable Integer user_id, @PathVariable Integer house_id) {
        wishlistService.addHouseToWishlist(user_id, house_id);
        return "House added to the wishlist successfully!";
    }



     @GetMapping("/public/wishlist/get/{user_id}")
public ResponseEntity<List<WishlistResponse>> getUserWishlistWithHouseDetails(@PathVariable Integer user_id) {
    List<WishlistResponse> userWishlist = wishlistService.getUserWishlistWithHouseDetails(user_id);
    return ResponseEntity.ok(userWishlist);
}







    @GetMapping("/user/wishlist/{userId}")
    public ResponseEntity<List<WishlistItemDTO>> getUserWishlist(@PathVariable Integer userId) {
        List<WishlistItemDTO> wishlistItems = wishlistService.getUserWishlist(userId);
        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
    }

    @GetMapping("/public/sharewishlist/{user_id}")
      public ResponseEntity<?> generateShareLink(@PathVariable Integer user_id) {
        return wishlistService.generateWishlistShareLink(user_id)
                .map(link -> ResponseEntity.ok().body(Map.of("shareLink", link)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/public/wishlist/{shareId}")
    public ResponseEntity<List<WishlistItemDTO>> getWishlistByShareId(@PathVariable String shareId) {
        List<WishlistItemDTO> wishlistItems = wishlistService.getWishlistByShareId(shareId);
        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
    }
}
