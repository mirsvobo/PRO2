package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Inventory;
import com.example.warehousemanagement.model.InventoryItem;
import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.repository.InventoryRepository;
import com.example.warehousemanagement.repository.InventoryItemRepository;
import com.example.warehousemanagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void saveInventoryItem(InventoryItem inventoryItem) {
        inventoryItemRepository.save(inventoryItem);
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    public Map<Item, Integer> getItemsWithTotalQuantity() {
        List<Item> items = itemRepository.findAll();
        Map<Item, Integer> itemQuantityMap = new HashMap<>();

        for (Item item : items) {
            int totalQuantity = 0;
            for (ProductVariant variant : item.getVariants()) {
                totalQuantity += variant.getQuantity();
            }
            itemQuantityMap.put(item, totalQuantity);
        }

        return itemQuantityMap;
    }
}
