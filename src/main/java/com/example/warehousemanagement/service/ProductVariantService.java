package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantService {

    @Autowired
    private ProductVariantRepository variantRepository;

    public ProductVariant findById(Long id) {
        return variantRepository.findById(id).orElse(null);
    }

    public void save(ProductVariant variant) {
        variantRepository.save(variant);
    }
    public List<ProductVariant> findAll() {
        return variantRepository.findAll();
    }
}
