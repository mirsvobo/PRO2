package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
