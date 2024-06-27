package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}