package com.cozygo.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozygo.be.entity.House;

public interface HouseRepo extends JpaRepository<House, Integer> {
    
}
