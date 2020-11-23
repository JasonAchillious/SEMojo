package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.entities.SourceCode;
import com.example.v1.semojo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public class SourceCodeController {
    @Autowired
    FileService fileService;

    @PostMapping("/contributor/{username}/product/{productId}/code")
    public WebRespResult uploadSourceCode(@PathVariable String username,
                                          @PathVariable Long productId,
                                          @RequestParam String description,
                                          @RequestParam String lang,
                                          MultipartFile sourceCode,
                                          HttpServletRequest req){
        try {
            SourceCode code = fileService.uploadSourceCode(username, productId, description, lang, sourceCode, req);
            return new WebRespResult<>(200, "upload success", code);
        } catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }
}
