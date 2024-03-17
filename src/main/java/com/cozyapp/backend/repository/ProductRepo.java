package com.cozyapp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozyapp.backend.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
