package com.cozyapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozyapp.backend.entity.House;

@Repository
public interface HouseRepo extends JpaRepository<House, Integer> {
}

