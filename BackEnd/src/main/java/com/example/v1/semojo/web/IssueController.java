package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
public class IssueController {
    @GetMapping("/product/{productId}/issues")
    public WebRespResult getIssues(@PathVariable Long productId){
        return null;
    }

    @PostMapping("customer/{username}/product/{productId}/issue")
    public WebRespResult addIssue(@PathVariable String username, @PathVariable Long productId){
        return null;
    }

    @PutMapping("customer/{username}/product/{productId}/issue/{issueId}")
    public WebRespResult updateIssue(@PathVariable String username,
                                     @PathVariable Long productId,
                                     @PathVariable Long issueId){
        return null;
    }

    @DeleteMapping("contributor/{username}/product/{productId}/issue/{issueId}")
    public WebRespResult deleteIssue(@PathVariable String username,
                                     @PathVariable Long productId,
                                     @PathVariable Long issueId){
        return null;
    }

    @GetMapping("/product/{productId}/issue/{issueId}/sub_issues")
    public WebRespResult getSubIssues(@PathVariable Long productId, @PathVariable Long issueId){
        return null;
    }

    @PostMapping("/customer/{username}/product/{productId}/issue/{issueId}/sub_issue")
    public WebRespResult addSubIssues(@PathVariable Long productId, @PathVariable Long issueId){
        return null;
    }

    @PutMapping("/customer/{username}/product/{productId}/issue/{issueId}/sub_issue/{subIssueId}")
    public WebRespResult updateSubIssues(@PathVariable Long productId, @PathVariable Long issueId){
        return null;
    }

    @DeleteMapping("/admin/{username}/product/{productId}/issue/{issueId}/sub_issue/{subIssueId}")
    public WebRespResult deleteSubIssues(@PathVariable Long productId, @PathVariable Long issueId){
        return null;
    }
}
