package com.example.warehousemanagement;

import com.example.warehousemanagement.model.Supplier;
import com.example.warehousemanagement.repository.SupplierRepository;
import com.example.warehousemanagement.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSupplierById() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Coca Cola Company");

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        Optional<Supplier> found = supplierService.getSupplierById(1L);

        assertTrue(found.isPresent());
        assertEquals("Coca Cola Company", found.get().getName());
    }

    @Test
    public void testCreateSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("Coca Cola Company");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier created = supplierService.createSupplier(supplier);

        assertNotNull(created);
        assertEquals("Coca Cola Company", created.getName());
    }
}
