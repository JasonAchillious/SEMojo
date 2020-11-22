package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
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
import java.util.UUID;


@RestController
public class AdditionalFileController {
    @Autowired
    FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(AdditionalFileController.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String uploadDir = "/uploadFile/";
    String type = "/additionalFile/";

    @RequestMapping(value = "/contributor/{username}/product/{productId}/upload/additionalFile",
            method = RequestMethod.POST,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiOperation(value = "upload", notes = "upload file", tags = "customer", httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name="token", value="jwt", required=true, dataType="bear token")
    )
    public WebRespResult upload(@PathVariable String username,
                                          @PathVariable String productId,
                                          MultipartFile uploadFile,
                                          HttpServletRequest req){
        if (uploadFile.isEmpty()) {
            return new WebRespResult<>(400, "File is empty");
        }

        String realPath = req.getSession().getServletContext().getRealPath(uploadDir);

        File folder = new File(realPath , username +  type);

        if (!folder.isDirectory()){
            folder.mkdirs();
        }

        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."));
        try{
            uploadFile.transferTo(new File(folder, newName));
            //todo add database
            String filePath = req.getScheme()
                    + "://" + req.getServerName() + ":" + req.getServerPort()
                    + uploadDir + username +   type  + newName;
            return new WebRespResult<String>(200, "upload success", filePath);
        }catch (IOException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return new WebRespResult<>(500, "upload error");
    }
}