package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.ProductVariant;
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

    @GetMapping("/new")
    public String createVariantForm(Model model) {
        model.addAttribute("variant", new ProductVariant());
        return "create_variant";
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
            return "edit_variant";
        } else {
            return "redirect:/variants";
        }
    }

    @PostMapping("/{id}")
    public String updateVariant(@PathVariable Long id, @ModelAttribute("variant") ProductVariant variant) {
        ProductVariant existingVariant = variantService.findById(id);
        if (existingVariant != null) {
            existingVariant.setName(variant.getName());
            variantService.save(existingVariant);
            return "redirect:/variants";
        } else {
            return "redirect:/variants";
        }
    }
}

