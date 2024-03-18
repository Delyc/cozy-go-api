package com.cozyapp.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class WishlistItemId implements Serializable {
    private Integer userId;
    private Integer houseId;


    public WishlistItemId() {}

    public WishlistItemId(Integer userId, Integer houseId) {
        this.userId = userId;
        this.houseId = houseId;
    }
   
}

