package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.ProductVariant;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/variants")
public class ProductVariantController {

    @Autowired
    private ProductVariantService variantService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/new")
    public String createVariantForm(Model model) {
        model.addAttribute("variant", new ProductVariant());
        return "create-variant";
    }

    @PostMapping
    public String saveVariant(@ModelAttribute("variant") ProductVariant variant) {
        variantService.save(variant);
        return "redirect:/variants";
    }

    @GetMapping("/edit/{id}")
    public String editVariantForm(@PathVariable Long id, Model model) {
        ProductVariant variant = variantService.findById(id);
        if (variant != null) {
            model.addAttribute("variant", variant);
            model.addAttribute("items", itemService.findAll());
            return "edit-variant";
        } else {
            return "redirect:/variants";
        }
    }


    @PostMapping("/{id}")
    public String updateVariant(@PathVariable Long id, @ModelAttribute("variant") ProductVariant variant) {
        ProductVariant existingVariant = variantService.findById(id);
        if (existingVariant != null) {
            existingVariant.setName(variant.getName());
            existingVariant.setDescription(variant.getDescription());
            existingVariant.setPrice(variant.getPrice());
            existingVariant.setQuantity(variant.getQuantity());
            existingVariant.setItem(variant.getItem());
            variantService.save(existingVariant);
            return "redirect:/variants";
        } else {
            return "redirect:/variants";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteVariant(@PathVariable Long id) {
        variantService.deleteById(id);
        return "redirect:/variants";
    }

    @GetMapping
    public String listVariants(Model model) {
        model.addAttribute("variants", variantService.findAll());
        return "list-variants";
    }
}
