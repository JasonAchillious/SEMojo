package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.*;
import com.example.v1.semojo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Product product = productDao.findProductByProductId(productId);
        System.out.println(product.getProductName());
        List<AdditionalFile> additionalFileList;
        AdditionalFile newAddition = new AdditionalFile();
        newAddition.setLocation(location);
        newAddition.setDescription(description);
        newAddition.setUploader(username);
        newAddition.setUploadTime(new Timestamp(System.currentTimeMillis()));
        if (product.getAdditionalFiles() == null){
            additionalFileList= new ArrayList<>();
            additionalFileList.add(newAddition);
            product.setAdditionalFiles(additionalFileList);
            productDao.save(product);
            return newAddition;
        }else {
            additionalFileList = product.getAdditionalFiles();
            additionalFileList.add(newAddition);
            product.setAdditionalFiles(additionalFileList);
            productDao.save(product);
            return newAddition;
        }
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

    public Optional<AdditionalFile> getAdditionalFile(Long fileId){
        return additionalFileDao.findById(fileId);
    }

    public String getLocation(String type, Long fileId) throws Exception {
        switch (type){
            case "other": return additionalFileDao.findAdditionalFileById(fileId).getLocation();
            case "doc": return documentDao.findDocumentById(fileId).getLocation();
            case "artifact": return artifactDao.findArtifactById(fileId).getLocation();
            case "testcase": return testCaseDao.findTestCaseById(fileId).getLocation();
            default: throw new Exception("Type Wrong");
        }
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
