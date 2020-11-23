package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public class ArtifactController {
    @PostMapping("/contributor/{username}/product/{productId}/artifact")
    public WebRespResult uploadArtifact(@PathVariable String username,
                                        @PathVariable Long productId,
                                        @RequestParam String description,
                                        @RequestParam String version,
                                        @RequestParam String status,
                                        @RequestParam String lang,
                                        MultipartFile artifact){

        return null;
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

    @GetMapping("/{username}/product/{productId}/artifacts")
    public WebRespResult updateArtifact(@PathVariable String username,
                                        @PathVariable Long productId){

        return null;
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
