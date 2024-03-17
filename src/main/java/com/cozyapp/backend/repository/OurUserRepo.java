package com.cozyapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozyapp.backend.entity.OurUsers;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
}
