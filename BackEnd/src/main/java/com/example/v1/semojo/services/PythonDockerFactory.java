package com.example.v1.semojo.services;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import static com.example.v1.semojo.util.ConnectUtil.*;
import static com.example.v1.semojo.util.ConnectUtil.channelSftp;

public class PythonDockerFactory implements ArtifactsDockerFactory{
    @Override
    public void createImage(String filename) {
        runCmd("cd /root/ooad/python/"+filename+" ; docker build --build-arg file="+filename+" -t ooad:"+filename+" .");
    }

    @Override
    public void copyFile(String filename, String containerId) {
        runCmd("docker cp "+containerId+":/output.txt /root/ooad/python/"+filename+"/output.txt");
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
        String src = directory + "src/main/java/com/example/v1/semojo/file/" + filename + "/" + filename + ".py";
        String dst = "ooad/python/"+ filename + "/" + filename + ".jar";
        try {
            channelSftp.mkdir("ooad/python/"+filename);
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = directory + "src/main/java/com/example/v1/semojo/file/" + filename + "/input.txt";
        dst = "ooad/python/"+ filename + "/input.txt";
        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = directory + "src/main/java/com/example/v1/semojo/file/output.txt";
        dst = "ooad/python/"+ filename + "/output.txt";
        try {
            channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
            System.out.println("sftp upload file success! src: {" + src + "}, dst: {" + dst + "}");
        } catch (SftpException e) {
            System.out.println("sftp upload file failed! src: {" + src + "}, dst: {" + dst + "}");
            return false;
        }
        src = directory + "pythonDocker/Dockerfile";
        dst = "ooad/python/"+ filename + "/Dockerfile";
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
        String src = "ooad/python/"+ filename + "/output.txt";
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
