package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.FileResultEnum;
import com.example.v1.semojo.api.enums.FileType;
import com.example.v1.semojo.api.model.FileRespModel;
import com.example.v1.semojo.api.util.FileRespResultUtil;
import com.example.v1.semojo.api.util.RespUtil;
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
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@RestController
public class FileController {
    @Autowired
    FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    @RequestMapping(value = "/contributor/{username}/product/{productId}/other",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "upload", notes = "upload file", tags = "customer", httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name="token", value="jwt", required=true, dataType="bear token")
    )
    public WebRespResult uploadAddition(@PathVariable String username,
                                          @PathVariable Long productId,
                                          @RequestParam String description,
                                          MultipartFile uploadFile,
                                          HttpServletRequest req){

        try{
            AdditionalFile file = fileService.uploadAddition(productId, username, description, uploadFile, req);
           return new WebRespResult<>(200, "upload success", file);
        }catch (IOException e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return FileRespResultUtil.error(400, e.getMessage());
        }

    }

    @DeleteMapping("/contributor/{username}/product/{productId}/other/{fileId}")
    public WebRespResult deleteAdditionFile(@PathVariable String username,
                                        @PathVariable String projectId,
                                        @RequestParam String type,
                                        @PathVariable Long fileId){
        return null;
    }

    @PutMapping("/contributor/{username}/product/{productId}/other/{fileId}")
    public WebRespResult updateAdditionFile(@PathVariable String username,
                                    @PathVariable String projectId,
                                    @PathVariable Long fileId,
                                    MultipartFile uploadFile,
                                    HttpServletRequest req){
        return null;
    }

    @GetMapping("/product/{productId}/others")
    public WebRespResult getAdditionFileList(@PathVariable Long productId){
        try {
            List<AdditionalFile> additionalFileList = fileService.findAllAddition(productId);
            return new WebRespResult<>(200, "success", additionalFileList);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @GetMapping("/contributor/{username}/product/{productId}/file")
    public void downloadFile(@PathVariable String username,
                                      @PathVariable Long productId,
                                      @RequestParam Long fileId,
                                      @RequestParam String type,
                                      HttpServletResponse response
    ){
        try {
            String location = fileService.getLocation(type, fileId);
            File file = new File(location);
            if(file.exists()){
                response.setContentType("application/octet-stream");
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(),"utf8"));
                byte[] buffer = new byte[1024];
                OutputStream os = null;
                FileInputStream fis= new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();

        }
    }
}
