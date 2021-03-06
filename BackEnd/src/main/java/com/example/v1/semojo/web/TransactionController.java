package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.ProductID;

import com.example.v1.semojo.api.model.TransactionModel;
import com.example.v1.semojo.entities.Transaction;
import com.example.v1.semojo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transacService;

    @PostMapping("/customer/{username}/shoppingcart/transaction")
    public WebRespResult createTransaction(@PathVariable String username,
                              @RequestParam ProductID products){
        try{
            TransactionModel transactionModel = transacService.createTransaction(products.getProjectIds(), username);
            return new WebRespResult<>(200, "success", transactionModel);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @PostMapping("/customer/{username}/product/{productId}/transaction")
    public WebRespResult creatTransac(@PathVariable String username, @PathVariable Long productId){
        try{
            TransactionModel transactionModel = transacService.createTransaction(productId, username);
            return new WebRespResult<>(200, "success", transactionModel);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @PutMapping("/customer/{username}/transaction/{transactionId}")
    public WebRespResult changeTransactionStatus(@PathVariable String username,
                                                 @PathVariable Long transactionId,
                                                 @RequestParam String status){
        try{
            TransactionModel transactionModel = transacService.changeStatus(username, transactionId, status);
            return new WebRespResult<>(200, "success", transactionModel);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @GetMapping("/customer/{username}/transactions")
    public WebRespResult getUserTransactions(@PathVariable String username){
        try{
            List<TransactionModel> transactionModels = transacService.getUserTransactions(username);
            return new WebRespResult<>(200, "success", transactionModels);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @GetMapping("/admin/product/{productId}/transactions")
    public WebRespResult getUserProductsactions(@PathVariable Long productId){
        try{
            List<TransactionModel> transactionModels = transacService.getUserProductsactions(productId);
            return new WebRespResult<>(200, "success", transactionModels);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @GetMapping("/customer/{username}/transaction/{productId}")
    public WebRespResult getTransactionDetail(@PathVariable String username,
                                              @PathVariable Long productId){
        try{
            List<TransactionModel> transactionModels = transacService.getTransactionDetail(username, productId);
            return new WebRespResult<>(200, "success", transactionModels);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }
}
