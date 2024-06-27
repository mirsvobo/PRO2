package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantService {

    @Autowired
    private ProductVariantRepository productVariantRepository;

    public Optional<ProductVariant> findById(Long id) {
        return productVariantRepository.findById(id);
    }

    public void save(ProductVariant variant) {
        productVariantRepository.save(variant);
    }

    public List<ProductVariant> findAll() {
        return productVariantRepository.findAll();
    }

    public List<ProductVariant> findByItem(Item item) {
        return productVariantRepository.findByItem(item);
    }

    public void deleteById(Long id) {
        productVariantRepository.deleteById(id);
    }
}
