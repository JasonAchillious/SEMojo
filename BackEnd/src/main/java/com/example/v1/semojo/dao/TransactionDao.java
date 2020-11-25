package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    Transaction findTransactionById(long transactionId);
}
