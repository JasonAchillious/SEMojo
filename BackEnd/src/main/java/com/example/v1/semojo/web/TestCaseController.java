package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestCaseController {
    @GetMapping("/project/{projectId}/testcases")
    public WebRespResult getTestCaseList(@PathVariable Long projectId){
        return null;
    }

    @PutMapping("/contributor/{username}/project/{projectId}/testcase/{caseId}")
    public WebRespResult updateTestCase(@PathVariable Long projectId){
        return null;
    }

    @DeleteMapping("/contributor/{username}/project/{projectId}/testcase/{caseId}")
    public WebRespResult deleteTestCase(@PathVariable Long projectId){
        return null;
    }

    @PostMapping("/contributor/{username}/project/{projectId}/testcase")
    public WebRespResult uploadTestCase(@PathVariable Long projectId){
        return null;
    }
}
