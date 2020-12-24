package com.example.v1.semojo.services;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.File;

import static com.example.v1.semojo.util.ConnectUtil.*;

public class CDockerFactory implements ArtifactsDockerFactory {

    @Override
    public void createImage(String filename) {
        runCmd("cd /root/ooad/c/"+filename+" ; docker build --build-arg file="+filename+" -t ooad:"+filename+" .");
    }

    @Override
    public void copyFile(String filename, String containerId) {

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
        String src = "D:/program/IdeaProjects/SEMojo-v1/BackEnd/src/main/java/com/example/v1/semojo/file/" + filename + "/" + filename + ".jar";
        String dst = "ooad/c/" + filename + "/" + filename + ".jar";
        try {
//            channelSftp.mkdir("ooad/"+filename);
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = "D:/program/IdeaProjects/SEMojo-v1/BackEnd/src/main/java/com/example/v1/semojo/file/" + filename + "/input.txt";
        dst = "ooad/c/" + filename + "/input.txt";
        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = "D:/program/IdeaProjects/SEMojo-v1/BackEnd/src/main/java/com/example/v1/semojo/file/output.txt";
        dst = "ooad/c/" + filename + "/output.txt";
        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = "D:\\program\\IdeaProjects\\SEMojo-v1\\BackEnd\\cDocker\\Dockerfile";
        dst = "ooad/c/" + filename + "/Dockerfile";
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
        return false;
    }

}
