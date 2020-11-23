package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocController {
    @GetMapping("/project/{projectId}/docs")
    public WebRespResult getDocList(@PathVariable Long projectId){
        return null;
    }

    @PutMapping("/contributor/{username}/project/{projectId}/doc/{docId}")
    public WebRespResult updateDoc(@PathVariable Long projectId){
        return null;
    }

    @DeleteMapping("/contributor/{username}/project/{projectId}/doc/{docId}")
    public WebRespResult deleteDoc(@PathVariable Long projectId){
        return null;
    }

    @PostMapping("/contributor/{username}/project/{projectId}/doc")
    public WebRespResult uploadDoc(@PathVariable Long projectId){
        return null;
    }
}
