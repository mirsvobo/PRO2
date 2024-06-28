package com.example.warehousemanagement.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @NotNull
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @NotNull
    private Item item;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    @NotNull
    private ProductVariant productVariant;

    private int initialQuantity;
    private int delivered;
    private int finalQuantity;
    private int consumption;

    public InventoryItem() {
        this.consumption = 0;
    }

    // Gettery a settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
        updateConsumption();
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
        updateConsumption();
    }

    public int getFinalQuantity() {
        return finalQuantity;
    }

    public void setFinalQuantity(int finalQuantity) {
        this.finalQuantity = finalQuantity;
        updateConsumption();
    }

    public int getConsumption() {
        return consumption;
    }

    private void updateConsumption() {
        this.consumption = this.initialQuantity + this.delivered - this.finalQuantity;
    }
}
