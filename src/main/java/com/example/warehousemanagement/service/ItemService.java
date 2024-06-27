package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Item;
import org.springframework.stereotype.Service;
import com.example.warehousemanagement.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }
}