package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.model.Supplier;
import com.example.warehousemanagement.model.Category;
import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.SupplierService;
import com.example.warehousemanagement.service.CategoryService;
import com.example.warehousemanagement.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductVariantService productVariantService;

    @GetMapping
    public String listItems(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/new")
    public String createItemForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);

        List<Supplier> suppliers = supplierService.findAll();
        List<Category> categories = categoryService.findAll();
        List<ProductVariant> variants = productVariantService.findAll();

        model.addAttribute("suppliers", suppliers);
        model.addAttribute("categories", categories);
        model.addAttribute("variants", variants);

        return "create-item";
    }

    @PostMapping
    public String saveItem(@ModelAttribute("item") Item item) {
        itemService.save(item);
        return "redirect:/items";
    }
}
