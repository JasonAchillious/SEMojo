package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.*;
import com.example.v1.semojo.dao.mongoDAO.ArtifactMongoDao;
import com.example.v1.semojo.dao.mongoDAO.ProductMongoDao;
import com.example.v1.semojo.dao.mongoDAO.TextMongoDao;
import com.example.v1.semojo.entities.*;
import com.example.v1.semojo.entities.mongodb.ArtifactMongo;
import com.example.v1.semojo.entities.mongodb.ProductMongo;
import com.example.v1.semojo.entities.mongodb.TextMongo;
import com.example.v1.semojo.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
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
    @Autowired
    TextMongoDao textMongoDao;
    @Autowired
    ProductMongoDao productMongoDao;
    @Autowired
    ArtifactMongoDao artifactMongoDao;

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    public Artifact uploadArtifact(String username,
                                   Long productId,
                                   String description,
                                   String version,
                                   String status,
                                   String lang,
                                   MultipartFile artifact,
                                   HttpServletRequest req) throws Exception {
        String filePath = uploadFile(productId, artifact, "artifact", req);
        Product product = productDao.findProductByProductId(productId);
        List<Artifact> artifacts = product.getArtifacts();
        Artifact af = new Artifact();
        af.setDescription(description);
        af.setLocation(filePath);
        af.setUploadTime(new Timestamp(System.currentTimeMillis()));
        af.setVersion(version);
        af.setUploader(username);
        af.setUpdate_time(new Timestamp(System.currentTimeMillis()));
        af.setFileName(artifact.getOriginalFilename());
        switch (status){
            case "beta": af.setStatus(Artifact.ArtifactStatus.beta); break;
            case "finalVersion": af.setStatus(Artifact.ArtifactStatus.finalVersion); break;
            case "deprecated": af.setStatus(Artifact.ArtifactStatus.deprecated); break;
            default: throw new Exception("Not Such Status");
        }
        switch (lang){
            case "java": af.setLang(SourceCode.Lang.java); break;
            case "golang": af.setLang(SourceCode.Lang.golang); break;
            case "javascript": af.setLang(SourceCode.Lang.javascript); break;
            case "c++": af.setLang(SourceCode.Lang.cplusplus); break;
            case "c": af.setLang(SourceCode.Lang.c); break;
            case "python": af.setLang(SourceCode.Lang.python);
            default: throw new Exception("Not Such Language");
        }
        Artifact artifactFile;
        if (artifacts == null){
            artifacts = new ArrayList<>();
            artifacts.add(af);
            product.setArtifacts(artifacts);
            Product updateProduct = productDao.save(product);
            List<Artifact> newArtifactList = updateProduct.getArtifacts();
            artifactFile = newArtifactList.get(newArtifactList.size()-1);
        }else {
            artifacts.add(af);
            product.setArtifacts(artifacts);
            Product updateProduct = productDao.save(product);
            List<Artifact> newArtifactList = updateProduct.getArtifacts();
            artifactFile = newArtifactList.get(newArtifactList.size()-1);
        }
        File file = new File(filePath);
        insertArtifactMongo(productId, artifact, artifactFile.getId(), lang, filePath, file);
        return artifactFile;
    }

    public String uploadFile(Long productId,
                             MultipartFile uploadFile,
                             String type,
                             HttpServletRequest req) throws IOException {
        String uploadDir = "/uploadFile/";

        if (uploadFile.isEmpty()) {
            throw new IOException("File is Empty");
        }

        String productPath = "product/" + productId + "/" + type +"/";
        String realPath = req.getSession().getServletContext().getRealPath(uploadDir);
        File folder = createFolder(realPath , productPath);

        String oldName = uploadFile.getOriginalFilename();
        String newName = randomFileName(oldName);

        File file = new File(folder, newName);
        uploadFile.transferTo(file);
        String filePath = file.getPath();
        return filePath;
    }
    // todo add mongodb support
    public AdditionalFile uploadAddition(Long productId, String username, String description, MultipartFile uploadFile,
                                         HttpServletRequest req) throws IOException {
        String location = this.uploadFile(productId, uploadFile, "addition", req);
        Product product = productDao.findProductByProductId(productId);
        List<AdditionalFile> additionalFileList;
        AdditionalFile newAddition = new AdditionalFile();
        newAddition.setLocation(location);
        newAddition.setDescription(description);
        newAddition.setUploader(username);
        newAddition.setUploadTime(new Timestamp(System.currentTimeMillis()));
        newAddition.setFileName(uploadFile.getOriginalFilename());
        if (product.getAdditionalFiles() == null){
            additionalFileList= new ArrayList<>();
            additionalFileList.add(newAddition);
            product.setAdditionalFiles(additionalFileList);
            Product updateProduct =  productDao.save(product);
            List<AdditionalFile> fileList = updateProduct.getAdditionalFiles();
            return fileList.get(fileList.size()-1);
        }else {
            additionalFileList = product.getAdditionalFiles();
            additionalFileList.add(newAddition);
            product.setAdditionalFiles(additionalFileList);
            Product updateProduct =  productDao.save(product);
            List<AdditionalFile> fileList = updateProduct.getAdditionalFiles();
            return fileList.get(fileList.size()-1);
        }
    }
    // todo add mongodb support
    public Document uploadDoc(Long productId, String username, String description, MultipartFile uploadFile,
                              HttpServletRequest req) throws IOException {
        String location = this.uploadFile(productId, uploadFile, "doc", req);
        Product product = productDao.findProductByProductId(productId);
        List<Document> docList = product.getDocs();
        Document newDoc = new Document();
        newDoc.setLocation(location);
        newDoc.setDescription(description);
        newDoc.setUploader(username);
        newDoc.setUploadTime(new Timestamp(System.currentTimeMillis()));
        newDoc.setFileName(uploadFile.getOriginalFilename());
        newDoc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if (docList == null){
            docList = new ArrayList<>();
            docList.add(newDoc);
            product.setDocs(docList);
            Product updateProduct =  productDao.save(product);
            List<Document> newDocs = updateProduct.getDocs();
            return newDocs.get(newDocs.size()-1);
        }else {
            docList.add(newDoc);
            product.setDocs(docList);
            Product updateProduct =  productDao.save(product);
            List<Document> newDocs = updateProduct.getDocs();
            return newDocs.get(newDocs.size()-1);
        }
    }
    // todo add mongodb support
    public TestCase uploadTestCase(Long productId,
                                   String username,
                                   String description,
                                   String input,
                                   String output,
                                   String inputDescription,
                                   String outPutDescription,
                                   String status,
                                   MultipartFile uploadFile,
                                   HttpServletRequest req) throws Exception {
        String location = "TODO";
                //this.uploadFile(productId, uploadFile, "testcase", req);
        Product product = productDao.findProductByProductId(productId);
        List<TestCase> testCaseList= product.getTestCases();
        TestCase newTestCase = new TestCase();
        newTestCase.setLocation(location);
        newTestCase.setDescription(description);
        newTestCase.setUploader(username);
        newTestCase.setUploadTime(new Timestamp(System.currentTimeMillis()));
        newTestCase.setFileName(uploadFile.getOriginalFilename());
        newTestCase.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        newTestCase.setInput(input);
        newTestCase.setOutput(output);
        newTestCase.setInputDescription(inputDescription);
        newTestCase.setOutPutDescription(outPutDescription);
        switch (status){
            case "pass": newTestCase.setStatus(TestCase.testStatus.pass); break;
            case "fail": newTestCase.setStatus(TestCase.testStatus.fail); break;
            case "untested": newTestCase.setStatus(TestCase.testStatus.untested); break;
            default: throw new Exception("Not Such Status");
        }
        TestCase tc;
        if (testCaseList == null){
            testCaseList = new ArrayList<>();
            testCaseList.add(newTestCase);
            product.setTestCases(testCaseList);
            Product updateProduct =  productDao.save(product);
            List<TestCase> newCases = updateProduct.getTestCases();
            tc = newCases.get(newCases.size()-1);
        }else {
            testCaseList.add(newTestCase);
            product.setTestCases(testCaseList);
            Product updateProduct =  productDao.save(product);
            List<TestCase> newCases = updateProduct.getTestCases();
            tc = newCases.get(newCases.size()-1);
        }
        insertTextFile(productId, uploadFile, tc.getId(), "testcase");
        return tc;
    }

    public SourceCode uploadSourceCode(String username,
                                       Long productId,
                                       String description,
                                       String lang,
                                       MultipartFile sourceCode,
                                       HttpServletRequest req) throws Exception {

        String filePath = "TODO";
                //uploadFile(productId, sourceCode, "code", req);
        Product product = productDao.findProductByProductId(productId);
        List<SourceCode> sourceCodeList = product.getSourceCodes();
        SourceCode sc = new SourceCode();
        sc.setDescription(description);
        sc.setLocation(filePath);
        sc.setUploadTime(new Timestamp(System.currentTimeMillis()));
        sc.setUploader(username);
        sc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sc.setFileName(sourceCode.getOriginalFilename());

        SourceCode code;
        switch (lang){
            case "java": sc.setLang(SourceCode.Lang.java); break;
            case "golang": sc.setLang(SourceCode.Lang.golang); break;
            case "javascript": sc.setLang(SourceCode.Lang.javascript); break;
            case "c++": sc.setLang(SourceCode.Lang.cplusplus); break;
            case "c": sc.setLang(SourceCode.Lang.c); break;
            case "python": sc.setLang(SourceCode.Lang.python);
            default: throw new Exception("Not Such Language");
        }
        if (sourceCodeList == null){
            sourceCodeList = new ArrayList<>();
            sourceCodeList.add(sc);
            product.setSourceCodes(sourceCodeList);
            Product updateProduct = productDao.save(product);
            List<SourceCode> newSourceCodes = updateProduct.getSourceCodes();
            code = newSourceCodes.get(newSourceCodes.size()-1);
        }else {
            sourceCodeList.add(sc);
            product.setSourceCodes(sourceCodeList);
            Product updateProduct = productDao.save(product);
            List<SourceCode> newSourceCodes = updateProduct.getSourceCodes();
            code = newSourceCodes.get(newSourceCodes.size()-1);
        }
        insertTextFile(productId, sourceCode, code.getId(), "code");
        return code;
    }

    public Optional<AdditionalFile> getAdditionalFile(Long fileId){
        return additionalFileDao.findById(fileId);
    }

    public List<Artifact> findAllArtifacts(Long productId) throws Exception{
        Product product = productDao.findProductByProductId(productId);
        return product.getArtifacts();
    }

    public List<Document> findAllDocument(Long productId) throws Exception{
        Product product = productDao.findProductByProductId(productId);
        return product.getDocs();
    }

    public List<AdditionalFile> findAllAddition(Long productId) throws Exception{
        Product product = productDao.findProductByProductId(productId);
        return product.getAdditionalFiles();
    }

    public List<SourceCode> findAllSourceCode(Long productId) throws Exception{
        Product product = productDao.findProductByProductId(productId);
        return product.getSourceCodes();
    }

    public List<TestCase> findAllTestCase(Long productId) throws Exception{
        Product product = productDao.findProductByProductId(productId);
        return product.getTestCases();
    }

    public TextMongo findTextMongoById(long textId){
        return textMongoDao.findTextMongoByTextId(textId);
    }

    public ArtifactMongo findArtifactMongoById(long artifactId){
        return artifactMongoDao.findArtifactMongoByArtifactId(artifactId);
    }

    public String getLocation(String type, Long fileId) throws Exception {
        switch (type){
            case "other": return additionalFileDao.findAdditionalFileById(fileId).getLocation();
            case "doc": return documentDao.findDocumentById(fileId).getLocation();
            case "artifact": return artifactDao.findArtifactById(fileId).getLocation();
            case "testcase": return testCaseDao.findTestCaseById(fileId).getLocation();
            case "code": return sourceCodeDao.findSourceCodeById(fileId).getLocation();
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


    public TextMongo insertTextFile(Long productId, MultipartFile textFile,
                                    long textId, String contentType) throws Exception {
        StringWriter writer = new StringWriter();

        IOUtils.copy(textFile.getInputStream(), writer, StandardCharsets.UTF_8.name());
        String content = writer.toString();
        TextMongo textMongo = new TextMongo(productId, textId,
                textFile.getOriginalFilename(),
                contentType, textFile.getSize(),
                new Date(System.currentTimeMillis()) ,content);
        ProductMongo productMongo =  productMongoDao.findProductMongoByProductId(productId);
        if (productMongo != null){
            textMongo = textMongoDao.save(textMongo);
            List<TextMongo> textMongoList;
            if (productMongo.getTextFiles() != null) {
                textMongoList = productMongo.getTextFiles();
            }else{
                textMongoList = new ArrayList<>();
            }
            textMongoList.add(textMongo);
            productMongo.setTextFiles(textMongoList);
            productMongoDao.save(productMongo);
            return textMongo;
        }else {
            throw new Exception("Not such product in mongodb");
        }
    }

    public ArtifactMongo insertArtifactMongo(Long productId, MultipartFile artifact,
                                             long artifactId, String type, String path, File file) throws Exception {
        byte[] bFile = FileUtil.fileToByte(file);
        ArtifactMongo artifactMongo = new ArtifactMongo(productId, artifactId,
                artifact.getOriginalFilename(),
                type, artifact.getSize(),
                new Binary(bFile), new Date(System.currentTimeMillis()), path);
        ProductMongo productMongo =  productMongoDao.findProductMongoByProductId(productId);
        if (productMongo != null){
            artifactMongo = artifactMongoDao.save(artifactMongo);
            List<ArtifactMongo> artifactMongoList;
            if (productMongo.getArtifacts() != null) {
                artifactMongoList = productMongo.getArtifacts();
            }else{
                artifactMongoList = new ArrayList<>();
            }
            artifactMongoList.add(artifactMongo);
            productMongo.setArtifacts(artifactMongoList);
            productMongoDao.save(productMongo);
            return artifactMongo;
        }else {
            throw new Exception("Not such product in mongodb");
        }
    }

}
