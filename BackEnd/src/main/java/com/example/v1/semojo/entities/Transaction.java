package com.example.v1.semojo.entities;

import org.springframework.transaction.TransactionStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "transaction")
public class Transaction {
    @Id
    private Long id;
    private Timestamp createTime;

    private static enum transactionStatus {
        NotSubmit,
        WaitingProcess,
        Processing,
        PurchasedSuccess
    }
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @OneToOne
    private User user;

    @OneToMany(mappedBy = "transac")
    private List<Product> products;
}
