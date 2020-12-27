package com.example.v1.semojo.services;

import com.example.v1.semojo.dao.mongoDAO.ArtifactMongoDao;
import com.example.v1.semojo.dao.mongoDAO.ProductMongoDao;
import com.example.v1.semojo.dao.mongoDAO.TextMongoDao;
import com.example.v1.semojo.entities.mongodb.ArtifactMongo;
import com.example.v1.semojo.entities.mongodb.ProductMongo;
import com.example.v1.semojo.entities.mongodb.TextMongo;
import com.example.v1.semojo.util.ConnectUtil;
import com.example.v1.semojo.util.FileUtil;
import com.github.dockerjava.api.DockerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

import static com.example.v1.semojo.util.ConnectUtil.directory;
import static com.example.v1.semojo.util.ConnectUtil.runCmd;

@Service
public class DockerClientService{

    @Autowired
    TextMongoDao textMongoDao;
    @Autowired
    ProductMongoDao productMongoDao;
    @Autowired
    ArtifactMongoDao artifactMongoDao;

    public String dockerRun(long productId, long artifactsId, long testcaseId) {
//        Long textId, String name, String contentType, long size, Timestamp updateDate, String content, String path
        TextMongo textMongo = textMongoDao.findTextMongoByTextId(testcaseId);
        ArtifactMongo artifactMongo = artifactMongoDao.findArtifactMongoByArtifactId(artifactsId);
        ProductMongo productMongo = productMongoDao.findProductMongoByProductId(productId);
        String filename = artifactMongo.getName();
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                filename = filename.substring(0, dot);
            }
        }
        FileUtil.bytesToFile(artifactMongo.getContent().getData(),directory + "src/main/java/com/example/v1/semojo/file/"+filename+"/"+filename+".jar");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(directory + "src/main/java/com/example/v1/semojo/file/"+ filename +"/input.txt");
            fileWriter.write(textMongo.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        DockerClient dockerClient = ConnectUtil.initClient();
        ConnectUtil.createChannel();
        ArtifactsDockerFactory dockerFactory = new JAVADockerFactory();
        dockerFactory.uploadFile(filename);
        dockerFactory.createImage(filename);

        runCmd("cd /root/ooad/java/"+filename+"; docker run -i ooad:"+filename+" < input.txt > output.txt");
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dockerFactory.getFile(filename);
        ConnectUtil.closeChannel();
        File file = new File(directory + "src/main/java/com/example/v1/semojo/file/"+filename+"/output.txt");
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= null;
        try {
            bf = new BufferedReader(new FileReader(directory + "src/main/java/com/example/v1/semojo/file/"+filename+"/output.txt"));
            String s = null;
            while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
                buffer.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ConnectUtil.closeClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
