package com.example.warehousemanagement.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Supplier supplier;

    @ManyToMany
    private Set<ProductVariant> variants;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(Set<ProductVariant> variants) {
        this.variants = variants;
    }
}
