package com.example.warehousemanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ElementCollection
    private List<Long> itemIds;

    @ElementCollection
    @MapKeyColumn(name = "variant_id")
    @Column(name = "quantity")
    private Map<Long, Integer> itemQuantities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }

    public Map<Long, Integer> getItemQuantities() {
        return itemQuantities;
    }

    public void setItemQuantities(Map<Long, Integer> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }
}