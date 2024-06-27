package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Inventory;
import com.example.warehousemanagement.model.InventoryItem;
import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.model.ItemWithTotalQuantity;
import com.example.warehousemanagement.repository.InventoryRepository;
import com.example.warehousemanagement.repository.InventoryItemRepository;
import com.example.warehousemanagement.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void saveInventoryItem(InventoryItem inventoryItem) {
        inventoryItemRepository.save(inventoryItem);
    }

    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }

    public void calculateConsumption(Inventory inventory) {
        for (InventoryItem item : inventory.getInventoryItems()) {
            ProductVariant variant = item.getProductVariant();
            if (variant != null) {
                int consumption = variant.calculateConsumption();
                // Logika pro uložení inventury a spotřeby
                System.out.println("Saving consumption for variant " + variant.getName() + ": " + consumption);
            } else {
                System.err.println("ProductVariant is null for item: " + item.getId());
            }
        }
        // Další logika pro uložení inventury, pokud je potřeba
    }

    public List<ItemWithTotalQuantity> getItemsWithTotalQuantity() {
        List<ProductVariant> variants = productVariantRepository.findAll();
        return variants.stream()
                .collect(Collectors.groupingBy(ProductVariant::getItem))
                .entrySet().stream()
                .map(entry -> {
                    ItemWithTotalQuantity itemWithTotalQuantity = new ItemWithTotalQuantity();
                    itemWithTotalQuantity.setItem(entry.getKey());
                    itemWithTotalQuantity.setTotalQuantity(entry.getValue().stream().mapToInt(ProductVariant::getFinalQuantity).sum());
                    return itemWithTotalQuantity;
                })
                .collect(Collectors.toList());
    }
}
