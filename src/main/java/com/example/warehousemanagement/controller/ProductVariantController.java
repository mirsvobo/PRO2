package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Item;
import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/variants")
public class ProductVariantController {

    @Autowired
    private ProductVariantService productVariantService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        ProductVariant variant = new ProductVariant();
        List<Item> items = itemService.findAll();
        model.addAttribute("variant", variant);
        model.addAttribute("items", items);
        return "create-variant";
    }

    @PostMapping
    public String saveVariant(@ModelAttribute ProductVariant variant, Model model) {
        Optional<Item> itemOptional = itemService.findById(variant.getItem().getId());
        if (itemOptional.isPresent()) {
            variant.setItem(itemOptional.get());
            productVariantService.save(variant);
            return "redirect:/variants";
        } else {
            model.addAttribute("errorMessage", "Item not found");
            List<Item> items = itemService.findAll();
            model.addAttribute("items", items);
            return "create-variant";
        }
    }

    @GetMapping
    public String listVariants(Model model) {
        List<ProductVariant> variants = productVariantService.findAll();
        model.addAttribute("variants", variants);
        return "list-variants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<ProductVariant> variantOptional = productVariantService.findById(id);
        if (!variantOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid variant Id:" + id);
        }
        ProductVariant variant = variantOptional.get();
        List<Item> items = itemService.findAll();
        model.addAttribute("variant", variant);
        model.addAttribute("items", items);
        return "edit-variant";
    }

    @PostMapping("/edit/{id}")
    public String updateVariant(@PathVariable("id") Long id, @ModelAttribute ProductVariant variant, Model model) {
        Optional<Item> itemOptional = itemService.findById(variant.getItem().getId());
        if (itemOptional.isPresent()) {
            variant.setItem(itemOptional.get());
            productVariantService.save(variant);
            return "redirect:/variants";
        } else {
            model.addAttribute("errorMessage", "Item not found");
            List<Item> items = itemService.findAll();
            model.addAttribute("items", items);
            return "edit-variant";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVariant(@PathVariable("id") Long id) {
        productVariantService.deleteById(id);
        return "redirect:/variants";
    }
}
