package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.Receipt;
import com.example.warehousemanagement.service.ItemService;
import com.example.warehousemanagement.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ItemService itemService;

    public ReceiptController(ReceiptService receiptService, ItemService itemService) {
        this.receiptService = receiptService;
        this.itemService = itemService;
    }

    @GetMapping
    public String listReceipts(Model model) {
        model.addAttribute("receipts", receiptService.findAll());
        return "receipts";
    }

    @GetMapping("/new")
    public String createReceiptForm(Model model) {
        model.addAttribute("receipt", new Receipt());
        model.addAttribute("items", itemService.findAll());
        return "create-receipt";
    }

    @PostMapping
    public String saveReceipt(@ModelAttribute("receipt") Receipt receipt) {
        receiptService.save(receipt);
        return "redirect:/receipts";
    }

    @GetMapping("/edit/{id}")
    public String editReceiptForm(@PathVariable Long id, Model model) {
        Receipt receipt = receiptService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid receipt Id:" + id));
        model.addAttribute("receipt", receipt);
        model.addAttribute("items", itemService.findAll());
        return "edit_receipt";
    }

    @PostMapping("/{id}")
    public String updateReceipt(@PathVariable Long id, @ModelAttribute("receipt") Receipt receipt) {
        receiptService.save(receipt);
        return "redirect:/receipts";
    }

    @GetMapping("/delete/{id}")
    public String deleteReceipt(@PathVariable Long id) {
        receiptService.delete(id);
        return "redirect:/receipts";
    }
}

