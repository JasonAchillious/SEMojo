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
import java.util.List;

@RestController
public class DocController {
    @Autowired
    FileService fileService;

    @GetMapping("/product/{productId}/docs")
    public WebRespResult getDocList(@PathVariable Long productId){
        try {
            List<Document> docs = fileService.findAllDocument(productId);
            return new WebRespResult<>(200, "success", docs);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @PutMapping("/contributor/{username}/product/{productId}/doc/{docId}")
    public WebRespResult updateDoc(@PathVariable Long productId){
        return null;
    }

    @DeleteMapping("/contributor/{username}/product/{productId}/doc/{docId}")
    public WebRespResult deleteDoc(@PathVariable Long productId){
        return null;
    }

    @PostMapping("/contributor/{username}/product/{productId}/doc")
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
