package com.cozyapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozyapp.backend.entity.Availability;

@Repository
public interface AvailabilityRepo extends JpaRepository<Availability, Integer>{

    
} 
