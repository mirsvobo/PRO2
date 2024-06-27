package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Inventory;
import com.example.warehousemanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public String getAllInventories(Model model) {
        List<Inventory> inventories = inventoryService.getAllInventories();
        model.addAttribute("inventories", inventories);
        return "inventory_list";
    }

    @GetMapping("/{id}")
    public String getInventoryById(@PathVariable Long id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id).orElse(null);
        model.addAttribute("inventory", inventory);
        return "inventory_detail";
    }

    @GetMapping("/add")
    public String showAddInventoryForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        return "inventory_form";
    }

    @PostMapping("/add")
    public String addInventory(@ModelAttribute Inventory inventory) {
        inventoryService.saveInventory(inventory);
        return "redirect:/inventories";
    }

    @GetMapping("/edit/{id}")
    public String showEditInventoryForm(@PathVariable Long id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id).orElse(null);
        model.addAttribute("inventory", inventory);
        return "inventory_form";
    }

    @PostMapping("/edit/{id}")
    public String editInventory(@PathVariable Long id, @ModelAttribute Inventory inventory) {
        inventory.setId(id);
        inventoryService.saveInventory(inventory);
        return "redirect:/inventories";
    }

    @GetMapping("/delete/{id}")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return "redirect:/inventories";
    }

    @PostMapping("/calculateConsumption/{id}")
    public String calculateConsumption(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id).orElse(null);
        if (inventory != null) {
            inventoryService.calculateConsumptionForAllItems(inventory);
        }
        return "redirect:/inventories";
    }
}
