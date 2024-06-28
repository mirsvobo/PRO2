package com.example.warehousemanagement;

import com.example.warehousemanagement.model.Supplier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SupplierTest {

    @Test
    public void testSupplierCreation() {
        Supplier supplier = new Supplier();
        supplier.setName("Coca Cola Company");

        assertNotNull(supplier);
        assertEquals("Coca Cola Company", supplier.getName());
    }
}
