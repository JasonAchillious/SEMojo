package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.FileType;
import com.example.v1.semojo.entities.AdditionalFile;
import com.example.v1.semojo.services.FileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;


@RestController
public class FileController {
    @Autowired
    FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String uploadDir = "/uploadFile/";


    @RequestMapping(value = "/contributor/{username}/product/{productId}/upload",
            method = RequestMethod.POST,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiOperation(value = "upload", notes = "upload file", tags = "customer", httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name="token", value="jwt", required=true, dataType="bear token")
    )
    public WebRespResult uploadFile(@PathVariable String username,
                                          @PathVariable Long productId,
                                          @RequestParam String description,
                                          @RequestParam String type,
                                          MultipartFile uploadFile,
                                          HttpServletRequest req){
        if (uploadFile.isEmpty()) {
            return new WebRespResult<>(400, "File is Empty");
        }
        if (!type.equals("doc") && !type.equals("other")){
            return new WebRespResult<>(400, "Wrong Type");
        }


        String productPath = "/product" + productId + "/"+ username + "/" + type + "/";
        String realPath = req.getSession().getServletContext().getRealPath(uploadDir);
        File folder = fileService.createFolder(realPath , productPath);

        String oldName = uploadFile.getOriginalFilename();
        String newName = fileService.randomFileName(oldName);
        try{
            uploadFile.transferTo(new File(folder, newName));
            String filePath = req.getScheme()
                    + "://" + req.getServerName() + ":" + req.getServerPort()
                    + uploadDir + productPath;
            switch (type) {
                case  "/other/" : return new WebRespResult<>(200,
                        "upload success",
                        fileService.uploadAddition(productId, username, description, filePath));
                default: return new WebRespResult(400, "wrong type");
            }

        }catch (IOException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return new WebRespResult<>(500, "upload error");
    }

    @DeleteMapping("/contributor/{username}/product/{productId}/{fileId}")
    public WebRespResult deleteFile(@PathVariable String username,
                                        @PathVariable String projectId,
                                        @RequestParam String type,
                                        @PathVariable Long fileId){
        return null;
    }

    @PutMapping("/contributor/{username}/product/{productId}/doc/{docId}")
    public WebRespResult updateFile(@PathVariable String username,
                                    @PathVariable String projectId,
                                    @PathVariable Long docId,
                                    MultipartFile uploadFile,
                                    HttpServletRequest req){
        return null;
    }

    @GetMapping("/customer/{username}/product/{productId}/{fileId}")
    public WebRespResult downloadFile(@RequestParam String type,
                                      @PathVariable String username,
                                      @PathVariable String projectId,
                                      @PathVariable Long fileId){
        return null;
    }
}
