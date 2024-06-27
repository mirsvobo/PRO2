package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Inventory;
import org.springframework.stereotype.Service;
import com.example.warehousemanagement.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }
}
