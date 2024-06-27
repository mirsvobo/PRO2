package com.example.warehousemanagement.repository;


import com.example.warehousemanagement.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
}