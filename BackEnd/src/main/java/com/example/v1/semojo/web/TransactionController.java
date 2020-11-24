package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @PostMapping("/customer/{username}/project/{projectId}/transaction")
    public WebRespResult startTransaction(@PathVariable String username,
                                          @PathVariable Long projectId){
        return null;
    }

    @PutMapping("/customer/{username}/project/{projectId}/transaction")
    public WebRespResult changeTransactionStatus(@PathVariable String username,
                                                 @PathVariable Long projectId,
                                                 @RequestParam String status){
        return null;
    }

    @GetMapping("/customer/{username}/transactions")
    public WebRespResult getUserTransactions(@PathVariable String username){
        return null;
    }

    @GetMapping("/admin/project/{projectId}/transactions")
    public WebRespResult getUserProjectsactions(@PathVariable Long projectId){
        return null;
    }

    @GetMapping("/customer/{username}/transaction/{transactionId}")
    public WebRespResult getTransactionDetail(@PathVariable String username,
                                              @PathVariable Long projectId){
        return null;
    }
}
