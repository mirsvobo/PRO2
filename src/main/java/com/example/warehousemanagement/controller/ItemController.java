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

import java.util.List;

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
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "list-items";
    }


    @GetMapping("/items/new")
    public String showCreateItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "create-item";
    }

    @PostMapping("/items")
    public String createItem(Item item) {
        itemService.save(item);
        return "redirect:/items";
    }

}
