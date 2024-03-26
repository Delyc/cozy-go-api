package com.cozyapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.entity.Wishlist;


@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    // @Modifying
    // @Transactional
    // @Query("DELETE FROM Wishlist w WHERE w.user.id = :user_id")
    // void deleteByUserId(@Param("user_id") Long user_id);
    void deleteByUserId(Integer userId);

    Wishlist findByUserAndHouse(OurUsers user, House house);
}