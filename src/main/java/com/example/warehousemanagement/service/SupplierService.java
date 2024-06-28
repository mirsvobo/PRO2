package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Supplier;
import com.example.warehousemanagement.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }

    public Optional<Supplier> getSupplierById(long l) {
        return supplierRepository.findById(l);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
}