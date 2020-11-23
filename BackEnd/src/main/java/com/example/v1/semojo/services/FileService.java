package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.*;
import com.example.v1.semojo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    ArtifactDao artifactDao;
    @Autowired
    DocumentDao documentDao;
    @Autowired
    SourceCodeDao sourceCodeDao;
    @Autowired
    TestCaseDao testCaseDao;
    @Autowired
    AdditionalFileDao additionalFileDao;
    @Autowired
    ProductDao productDao;

    public Artifact uploadArtifact(Long productId, String username){
        return null;
    }

    public AdditionalFile uploadAddition(Long productId, String username, String description, String location){
        Product product = productDao.findProductById(productId);
        List<AdditionalFile> additionalFileList = product.getAdditionalFiles();
        AdditionalFile newAddition = new AdditionalFile();
        newAddition.setLocation(location);
        newAddition.setDescription(description);
        newAddition.setUploader(username);
        newAddition.setUploadTime(new Timestamp(System.currentTimeMillis()));
        additionalFileList.add(newAddition);
        product.setAdditionalFiles(additionalFileList);
        productDao.save(product);
        return newAddition;
    }

    public Document uploadDoc(){
        return null;
    }

    public TestCase uploadTestCase(){
        return null;
    }

    public SourceCode uploadSourceCode(){
        return null;
    }

    public String randomFileName(String oldName){
        return UUID.randomUUID().toString() + getPostfix(oldName);
    }

    public String getPostfix(String oldName){
        return oldName.substring(oldName.lastIndexOf("."));
    }

    public File createFolder(String realPath, String child){
        File folder = new File(realPath , child);

        if (!folder.isDirectory()){
            folder.mkdirs();
        }
        return folder;
    }
}
