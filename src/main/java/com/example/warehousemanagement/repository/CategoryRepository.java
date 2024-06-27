package com.example.warehousemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.warehousemanagement.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}