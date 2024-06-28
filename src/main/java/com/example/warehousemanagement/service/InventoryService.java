package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Inventory;
import com.example.warehousemanagement.model.InventoryItem;
import com.example.warehousemanagement.repository.InventoryItemRepository;
import com.example.warehousemanagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAllByOrderByDateDesc();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<InventoryItem> getItemsByInventoryId(Long inventoryId) {
        return inventoryItemRepository.findByInventoryId(inventoryId);
    }

    public Inventory findById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }
}


