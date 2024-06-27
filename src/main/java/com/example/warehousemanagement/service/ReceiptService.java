package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.Receipt;
import org.springframework.stereotype.Service;
import com.example.warehousemanagement.repository.ReceiptRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public List<Receipt> findAll() {
        return receiptRepository.findAll();
    }

    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public void delete(Long id) {
        receiptRepository.deleteById(id);
    }
    public Optional<Receipt> findById(Long id) {
        return receiptRepository.findById(id);
    }
}
