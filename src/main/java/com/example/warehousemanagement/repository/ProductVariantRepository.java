package com.example.warehousemanagement.repository;


import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByItem(Item item);
}