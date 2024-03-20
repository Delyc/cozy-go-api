package com.cozyapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozyapp.backend.entity.BookingVisit;

@Repository
public interface BookingVisitRepo extends JpaRepository<BookingVisit, Integer>{

    
} 