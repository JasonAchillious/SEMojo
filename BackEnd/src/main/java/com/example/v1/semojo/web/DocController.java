package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.util.FileRespResultUtil;
import com.example.v1.semojo.entities.Document;
import com.example.v1.semojo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class DocController {
    @Autowired
    FileService fileService;

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
    public WebRespResult uploadDoc(@PathVariable String username,
                                   @PathVariable Long productId,
                                   @RequestParam String description,
                                   MultipartFile document,
                                   HttpServletRequest req){
        try{
            Document file = fileService.uploadDoc(productId, username, description, document, req);
            return new WebRespResult<>(200, "upload success", file);
        }catch (IOException e){
            e.printStackTrace();
            return FileRespResultUtil.error(400, e.getMessage());
        }
    }
}
