package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
