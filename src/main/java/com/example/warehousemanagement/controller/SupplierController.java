package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Supplier;
import com.example.warehousemanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return "list-suppliers";
    }

    @GetMapping("/new")
    public String createSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "create-supplier";
    }

    @PostMapping
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String editSupplierForm(@PathVariable Long id, Model model) {
        Supplier supplier = supplierService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid supplier Id:" + id));
        model.addAttribute("supplier", supplier);
        return "edit-supplier";
    }

    @PutMapping("/edit/{id}")
    public String updateSupplier(@PathVariable Long id, @ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplierById(id);
        return "redirect:/suppliers";
    }
}
