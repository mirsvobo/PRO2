package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByInventoryId(Long inventoryId);

}
