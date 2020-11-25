package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.ProductID;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transacService;

    @PostMapping("/customer/{username}/transaction")
    public WebRespResult crea(@PathVariable String username,
                              @RequestParam ProductID products){
        return null;
    }

    @PutMapping("/customer/{username}/transaction/{transaction")
    public WebRespResult changeTransactionStatus(@PathVariable String username,
                                                 @PathVariable Long projectId,
                                                 @RequestParam String status){
        return null;
    }

    @GetMapping("/customer/{username}/transactions")
    public WebRespResult getUserTransactions(@PathVariable String username){
        return null;
    }

    @GetMapping("/admin/project/{productId}/transactions")
    public WebRespResult getUserProjectsactions(@PathVariable Long projectId){
        return null;
    }

    @GetMapping("/customer/{username}/transaction/{transactionId}")
    public WebRespResult getTransactionDetail(@PathVariable String username,
                                              @PathVariable Long projectId){
        return null;
    }
}
