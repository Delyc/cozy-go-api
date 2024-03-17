package com.cozygo.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozygo.be.entity.OurUsers;

public interface OurUserRepo  extends JpaRepository<OurUsers, Integer>{

    Optional<OurUsers> findByEmail (String email);
    
}
