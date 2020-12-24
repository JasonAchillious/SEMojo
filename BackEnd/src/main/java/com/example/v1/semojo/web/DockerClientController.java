package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.entities.Document;
import com.example.v1.semojo.services.DockerClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class DockerClientController {
    @Autowired
    DockerClientService dockerClientService;

    @GetMapping("/product/{productId}/artifacts/{artifactsId}/{testcaseId}")
    public WebRespResult getDocList(@PathVariable long productId,
                                    @PathVariable long artifactsId,
                                    @PathVariable long testcaseId){
        try {
            String output = dockerClientService.dockerRun(productId, artifactsId, testcaseId);
            return new WebRespResult<>(200, "success", output);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }
}
