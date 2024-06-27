package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Inventory;
import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.repository.ProductVariantRepository;
import com.example.warehousemanagement.service.InventoryService;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.ProductVariantService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryService inventoryService;
    private final ItemService itemService;
    private final ProductVariantService productVariantService;

    public InventoryController(InventoryService inventoryService, ItemService itemService, ProductVariantService productVariantService) {
        this.inventoryService = inventoryService;
        this.itemService = itemService;
        this.productVariantService = productVariantService;
    }

    @GetMapping
    public String listInventories(Model model) {
        model.addAttribute("inventories", inventoryService.findAll());
        return "inventories";
    }

    @GetMapping("/new")
    public String createInventoryForm(Model model) {
        Inventory inventory = new Inventory();
        List<Item> items = itemService.findAll();
        List<ProductVariant> variants = productVariantService.findAll();

        model.addAttribute("inventory", inventory);
        model.addAttribute("items", items);
        model.addAttribute("variants", variants);

        return "create-inventory";
    }

    @PostMapping
    public String saveInventory(@ModelAttribute("inventory") Inventory inventory) {
        inventoryService.save(inventory);
        return "redirect:/inventories";
    }

    @GetMapping("/edit/{id}")
    public String editInventoryForm(@PathVariable Long id, Model model) {
        Inventory inventory = inventoryService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid inventory Id:" + id));
        model.addAttribute("inventory", inventory);
        model.addAttribute("items", itemService.findAll());
        return "edit_inventory";
    }

    @PostMapping("/{id}")
    public String updateInventory(@PathVariable Long id, @ModelAttribute("inventory") Inventory inventory) {
        inventoryService.save(inventory);
        return "redirect:/inventories";
    }

    @GetMapping("/delete/{id}")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.delete(id);
        return "redirect:/inventories";
    }
}