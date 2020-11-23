package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.entities.TestCase;
import com.example.v1.semojo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestCaseController {
    @Autowired
    FileService fileService;

    @GetMapping("/project/{projectId}/testcases")
    public WebRespResult getTestCaseList(@PathVariable Long projectId){
        return null;
    }

    @PutMapping("/contributor/{username}/project/{projectId}/testcase/{caseId}")
    public WebRespResult updateTestCase(@PathVariable Long projectId){
        return null;
    }

    @DeleteMapping("/contributor/{username}/project/{projectId}/testcase/{caseId}")
    public WebRespResult deleteTestCase(@PathVariable Long projectId,
                                        @PathVariable Long caseId){
        return null;
    }

    @PostMapping("/contributor/{username}/project/{projectId}/testcase")
    public WebRespResult uploadTestCase(@PathVariable Long projectId,
                                        @PathVariable String username,
                                        @RequestParam String description,
                                        @RequestParam String input,
                                        @RequestParam String output,
                                        @RequestParam String inputDescription,
                                        @RequestParam String outPutDescription,
                                        @RequestParam String status,
                                        MultipartFile uploadFile,
                                        HttpServletRequest req){
        try {
            TestCase testCase = fileService.uploadTestCase(projectId, username, description, input, output,
                    inputDescription, outPutDescription, status, uploadFile, req);
            return new WebRespResult<>(200, "upload success", testCase);
        } catch (Exception e) {
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }
}
