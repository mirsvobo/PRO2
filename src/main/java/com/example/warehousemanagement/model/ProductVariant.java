package com.example.warehousemanagement.model;

import jakarta.persistence.*;

@Entity
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int initialQuantity;
    private int receivedQuantity;
    private int finalQuantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    // Gettery a settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public int getFinalQuantity() {
        return finalQuantity;
    }

    public void setFinalQuantity(int finalQuantity) {
        this.finalQuantity = finalQuantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // Metoda pro výpočet spotřeby
    public int calculateConsumption() {
        return (initialQuantity + receivedQuantity) - finalQuantity;
    }
}
