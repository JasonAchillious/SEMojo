package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.util.FileRespResultUtil;
import com.example.v1.semojo.entities.Artifact;
import com.example.v1.semojo.services.FileService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class ArtifactController {
    @Autowired
    FileService fileService;

    @PostMapping("/contributor/{username}/product/{productId}/artifact")
    public WebRespResult uploadArtifact(@PathVariable String username,
                                        @PathVariable Long productId,
                                        @RequestParam String description,
                                        @RequestParam String version,
                                        @RequestParam String status,
                                        @RequestParam String lang,
                                        MultipartFile artifact,
                                        HttpServletRequest req){

        try{
            Artifact file = fileService.uploadArtifact(username, productId, description,
                    version, status, lang, artifact, req);
            return new WebRespResult<>(200, "upload success", file);
        }catch (IOException e){
            e.printStackTrace();
            return FileRespResultUtil.error(400, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return FileRespResultUtil.error(500, e.getMessage());
        }
    }

    @DeleteMapping("/contributor/{username}/product/{productId}/artifact/{artifactId}")
    public WebRespResult deleteArtifact(@PathVariable String username,
                                        @PathVariable Long productId,
                                        @PathVariable Long artifactId){

        return null;
    }

    @PutMapping("/contributor/{username}/product/{productId}/artifact/{artifactId}")
    public WebRespResult updateArtifact(@PathVariable String username,
                                        @PathVariable Long productId,
                                        @PathVariable Long artifactId,
                                        MultipartFile artifact){

        return null;
    }

    @GetMapping("/product/{productId}/artifacts")
    public WebRespResult getArtifactDetail(@PathVariable Long productId){
        try {
            List<Artifact> artifacts = fileService.findAllArtifacts(productId);
            return new WebRespResult<>(200, "success", artifacts);
        }catch (Exception e) {
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @PutMapping("/contributor/{username}/product/{productId}/artifactInfo/{artifactId}")
    public WebRespResult updateArtifactInfo(@PathVariable String username,
                                            @PathVariable Long productId,
                                            @PathVariable Long artifactId,
                                            @RequestParam String description,
                                            @RequestParam String version,
                                            @RequestParam String status,
                                            @RequestParam String lang){

        return null;
    }
}
