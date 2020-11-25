package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.ProductID;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.Transaction;
import com.example.v1.semojo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transacService;

    @PostMapping("/customer/{username}/shoppingcart/transaction")
    public WebRespResult createTransaction(@PathVariable String username,
                              @RequestParam ProductID products){
        try{
            Transaction transac = transacService.createTransaction(products.getProjectIds(), username);
            return new WebRespResult<>(200, "success", transac);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @PostMapping("/customer/{username}/product/{productId}/transaction")
    public WebRespResult creatTransac(@PathVariable String username, @PathVariable Long productId){
        try{
            Transaction transac = transacService.createTransaction(productId, username);
            return new WebRespResult<>(200, "success", transac);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @PutMapping("/customer/{username}/transaction/{transactionId}")
    public WebRespResult changeTransactionStatus(@PathVariable String username,
                                                 @PathVariable Long transactionId,
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
