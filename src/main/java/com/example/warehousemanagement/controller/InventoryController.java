package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Inventory;
import com.example.warehousemanagement.service.InventoryService;
import com.example.warehousemanagement.service.ItemService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryService inventoryService;
    private final ItemService itemService;

    public InventoryController(InventoryService inventoryService, ItemService itemService) {
        this.inventoryService = inventoryService;
        this.itemService = itemService;
    }

    @GetMapping
    public String listInventories(Model model) {
        model.addAttribute("inventories", inventoryService.findAll());
        return "inventories";
    }

    @GetMapping("/new")
    public String createInventoryForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        model.addAttribute("items", itemService.findAll());
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
