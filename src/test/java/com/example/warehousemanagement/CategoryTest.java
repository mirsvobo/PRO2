package com.example.warehousemanagement;

import com.example.warehousemanagement.model.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void testCategoryCreation() {
        Category category = new Category();
        category.setName("Soft Drinks");

        assertNotNull(category);
        assertEquals("Soft Drinks", category.getName());
    }
}
