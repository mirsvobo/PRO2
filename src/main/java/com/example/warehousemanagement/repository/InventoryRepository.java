package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByOrderByDateDesc();

}
