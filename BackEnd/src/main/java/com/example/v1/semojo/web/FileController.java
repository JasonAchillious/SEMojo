package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.FileResp;
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
public class FileController {
    @Autowired
    FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/contributor/{username}/upload",
            method = RequestMethod.POST,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiOperation(value = "upload", notes = "upload file", tags = "customer", httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name="token", value="jwt", required=true, dataType="bear token")
    )
    public WebRespResult upload(@PathVariable String username,
                                          @RequestParam String type,
                                          MultipartFile uploadFile,
                                          HttpServletRequest req){
        if (uploadFile.isEmpty()) {
            return new WebRespResult<>(400, "File is empty");
        }

        if (!type.equals("code") && !type.equals("doc") && !type.equals("artifact")){
            return new WebRespResult<>(400, "wrong type");
        }
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");

        File folder = new File(realPath , username + "/" + type);

        if (!folder.isDirectory()){
            folder.mkdirs();
        }

        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."));
        try{
            uploadFile.transferTo(new File(folder, newName));
            //todo add database
            String filePath = "";
            return new WebRespResult<>(200, "upload success", filePath);
        }catch (IOException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return new WebRespResult<>(500, "upload error");
    }
}
