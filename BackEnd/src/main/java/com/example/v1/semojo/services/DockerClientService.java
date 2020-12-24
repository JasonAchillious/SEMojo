package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ContainerModel;
import com.example.v1.semojo.api.model.DockerModel;
import com.example.v1.semojo.api.model.LoginRespModel;
import com.example.v1.semojo.dao.mongoDAO.ArtifactMongoDao;
import com.example.v1.semojo.dao.mongoDAO.ProductMongoDao;
import com.example.v1.semojo.dao.mongoDAO.TextMongoDao;
import com.example.v1.semojo.entities.mongodb.ArtifactMongo;
import com.example.v1.semojo.entities.mongodb.ProductMongo;
import com.example.v1.semojo.entities.mongodb.TextMongo;
import com.example.v1.semojo.util.ConnectUtil;
import com.example.v1.semojo.util.FileUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.jcraft.jsch.*;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

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
        FileUtil.bytesToFile(artifactMongo.getContent().getData(),"D:\\program\\IdeaProjects\\SEMojo-v1\\BackEnd\\src\\main\\java\\com\\example\\v1\\semojo\\file\\"+filename+"\\"+filename+".jar");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\program\\IdeaProjects\\SEMojo-v1\\BackEnd\\src\\main\\java\\com\\example\\v1\\semojo\\file\\"+ filename +"\\input.txt");
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

        String id = dockerClient.createContainerCmd("ooad:"+filename).withStdinOpen(Boolean.TRUE).exec().getId();
        System.out.println(id);
        dockerClient.startContainerCmd(id).exec();
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dockerFactory.copyFile(filename, id);
        dockerFactory.getFile(filename);
        ConnectUtil.closeChannel();
        File file = new File("D:\\program\\IdeaProjects\\SEMojo-v1\\BackEnd\\src\\main\\java\\com\\example\\v1\\semojo\\file\\"+filename+"\\output.txt");
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= null;
        try {
            bf = new BufferedReader(new FileReader("D:\\program\\IdeaProjects\\SEMojo-v1\\BackEnd\\src\\main\\java\\com\\example\\v1\\semojo\\file\\"+filename+"\\output.txt"));
            String s = null;
            while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
                buffer.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
