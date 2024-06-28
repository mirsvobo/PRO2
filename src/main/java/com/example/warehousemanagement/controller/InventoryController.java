package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Inventory;
import com.example.warehousemanagement.model.InventoryItem;
import com.example.warehousemanagement.service.InventoryService;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductVariantService productVariantService;

    @GetMapping
    public String listInventories(Model model) {
        model.addAttribute("inventories", inventoryService.getAllInventories());
        return "inventory-list";
    }

    @GetMapping("/{id}")
    public String getInventory(@PathVariable Long id, Model model) {
        model.addAttribute("inventory", inventoryService.getInventoryById(id));
        return "inventory-detail";
    }

    @GetMapping("/add")
    public String showAddInventoryForm(Model model) {
        Inventory inventory = new Inventory();

        itemService.getAllItems().forEach(item -> {
            productVariantService.getVariantsByItemId(item.getId()).forEach(variant -> {
                InventoryItem inventoryItem = new InventoryItem();
                inventoryItem.setItem(item);
                inventoryItem.setProductVariant(variant);
                inventoryItem.setInventory(inventory);
                inventory.getInventoryItems().add(inventoryItem);
            });
        });

        model.addAttribute("inventory", inventory);
        return "inventory-form";
    }

    @PostMapping
    public String saveInventory(@ModelAttribute Inventory inventory) {
        inventory.getInventoryItems().forEach(item -> {
            item.setInventory(inventory);
        });
        inventoryService.saveInventory(inventory);
        return "redirect:/inventories";
    }
    @GetMapping("/inventory/{id}")
    public String getInventoryDetails(@PathVariable Long id, Model model) {
        Inventory inventory = inventoryService.findById(id);
        if (inventory == null) {
            // Handle the case where inventory is not found
            return "error";
        }
        // Explicitně načíst položky pro zajištění lazy loadingu
        inventory.getInventoryItems().forEach(item -> item.getProductVariant().getName());
        model.addAttribute("inventory", inventory);
        return "inventory-detail";
}}
