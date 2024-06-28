package com.example.warehousemanagement;

import com.example.warehousemanagement.model.Category;
import com.example.warehousemanagement.repository.CategoryRepository;
import com.example.warehousemanagement.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Soft Drinks");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> found = categoryService.getCategoryById(1L);

        assertTrue(found.isPresent());
        assertEquals("Soft Drinks", found.get().getName());
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Soft Drinks");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category created = categoryService.createCategory(category);

        assertNotNull(created);
        assertEquals("Soft Drinks", created.getName());
    }
}
