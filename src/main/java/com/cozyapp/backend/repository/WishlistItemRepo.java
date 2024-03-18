package com.cozyapp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozyapp.backend.entity.WishlistItem;
import com.cozyapp.backend.entity.WishlistItemId;

public interface WishlistItemRepo extends JpaRepository<WishlistItem, WishlistItemId> {
    List<WishlistItem> findAllByUserId(Integer userId);

}
