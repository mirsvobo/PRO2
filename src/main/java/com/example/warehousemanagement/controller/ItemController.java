package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.CategoryService;
import com.example.warehousemanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "list-items";
    }

    @GetMapping("/new")
    public String createItemForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "create-item";
    }

    @PostMapping
    public String saveItem(@ModelAttribute("item") Item item) {
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        model.addAttribute("item", item);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "edit-item";
    }

    @PutMapping("/edit/{id}")
    public String updateItem(@PathVariable Long id, @ModelAttribute("item") Item item) {
        itemService.save(item);
        return "redirect:/items";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/items";
    }
}
