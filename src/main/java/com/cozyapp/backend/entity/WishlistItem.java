package com.cozyapp.backend.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class WishlistItem {
    @EmbeddedId
    private WishlistItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private OurUsers user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("houseId")
    @JoinColumn(name = "house_id")
    private House house;

    private LocalDateTime addedAt = LocalDateTime.now();

  
}
