package com.example.v1.semojo.services;

import com.example.v1.semojo.util.ConnectUtil;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.example.v1.semojo.services.DockerClientService.*;
import static com.example.v1.semojo.util.ConnectUtil.*;


public class JAVADockerFactory implements ArtifactsDockerFactory {

    @Override
    public void createImage(String filename) {
        runCmd("cd /root/ooad/java/"+filename+" ; docker build --build-arg file="+filename+" -t ooad:"+filename+" .");
    }

    @Override
    public void copyFile(String filename, String containerId) {
        runCmd("docker cp "+containerId+":/output.txt /root/ooad/java/"+filename+"/output.txt");
    }

    @Override
    public boolean uploadFile(String filename) {
        if (channelSftp == null) {
            System.out.println("need create channelSftp before upload file");
            return false;
        }

        if (channelSftp.isClosed()) {
            createChannel(); // 如果被关闭则应重新创建
        }
        String src = directory + "src/main/java/com/example/v1/semojo/file/" + filename + "/" + filename + ".jar";
        String dst = "ooad/java/"+ filename + "/" + filename + ".jar";
        try {
            channelSftp.mkdir("ooad/java/"+filename);
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = directory + "src/main/java/com/example/v1/semojo/file/" + filename + "/input.txt";
        dst = "ooad/java/"+ filename + "/input.txt";
        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = directory + "src/main/java/com/example/v1/semojo/file/output.txt";
        dst = "ooad/java/"+ filename + "/output.txt";
        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = directory + "javaDocker/Dockerfile";
        dst = "ooad/java/"+ filename + "/Dockerfile";
        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        return true;
    }

    @Override
    public boolean getFile(String filename) {
        if (channelSftp == null) {
            System.out.println("need create channelSftp before upload file");
            return false;
        }

        if (channelSftp.isClosed()) {
            createChannel(); // 如果被关闭则应重新创建
        }
        String src = "ooad/java/"+ filename + "/output.txt";
        String dst = directory + "src/main/java/com/example/v1/semojo/file/" + filename + "/output.txt";
        try {
            channelSftp.get(src, dst);
            System.out.println("sftp get file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp get file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        return true;
    }

}
