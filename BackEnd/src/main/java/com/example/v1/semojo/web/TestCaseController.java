package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.entities.TestCase;
import com.example.v1.semojo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TestCaseController {
    @Autowired
    FileService fileService;

    @GetMapping("/product/{productId}/testcases")
    public WebRespResult getTestCaseList(@PathVariable Long productId){
        try {
            List<TestCase> testCases = fileService.findAllTestCase(productId);
            return new WebRespResult<>(200, "success", testCases);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @PutMapping("/contributor/{username}/product/{productId}/testcase/{caseId}")
    public WebRespResult updateTestCase(@PathVariable Long productId,
                                        @PathVariable String username,
                                        @PathVariable Long caseId
    ){
        return null;
    }

    @DeleteMapping("/contributor/{username}/product/{productId}/testcase/{caseId}")
    public WebRespResult deleteTestCase(@PathVariable Long productId,
                                        @PathVariable Long caseId,
                                        @PathVariable String username){
        return null;
    }

    @PostMapping("/contributor/{username}/product/{productId}/testcase")
    public WebRespResult uploadTestCase(@PathVariable Long projectId,
                                        @PathVariable String username,
                                        @RequestParam String description,
                                        @RequestParam String input,
                                        @RequestParam String output,
                                        @RequestParam String inputDescription,
                                        @RequestParam String outputDescription,
                                        @RequestParam String status,
                                        MultipartFile uploadFile,
                                        HttpServletRequest req){
        try {
            TestCase testCase = fileService.uploadTestCase(projectId, username, description, input, output,
                    inputDescription, outputDescription, status, uploadFile, req);
            return new WebRespResult<>(200, "upload success", testCase);
        } catch (Exception e) {
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }
}
