package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.CategoryService;
import com.example.warehousemanagement.service.SupplierService;
import com.example.warehousemanagement.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductVariantService productVariantService;

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "item-list"; // Název šablony pro seznam itemů
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("variants", productVariantService.findAll());
        return "create-item"; // Název šablony pro vytvoření itemu
    }

    @PostMapping("/create")
    public String createItem(@ModelAttribute("item") Item item) {
        itemService.save(item);
        return "redirect:/items"; // Přesměrování po úspěšném vytvoření itemu
    }
}
