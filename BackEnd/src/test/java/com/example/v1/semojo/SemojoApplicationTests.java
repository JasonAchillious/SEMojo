package com.example.v1.semojo;

import com.example.v1.semojo.api.model.ContainerModel;
import com.example.v1.semojo.api.model.DockerModel;
import com.example.v1.semojo.services.DockerClientService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class SemojoApplicationTests {
//@Autowired
//DockerClientService dockerClientService;
    @Test
    void contextLoads() {
        //连接docker服务器
//        DockerClient dockerClient = dockerClientService.initClient();
//        DockerModel dockerModel = dockerClientService.getDockerInfo();
//        List<ContainerModel> containerModels = dockerClientService.getContainers();
//        System.out.println(containerModels.toString());
//        System.out.println(dockerModel.toString());

//        dockerClientService.createChannel();
////        dockerClientService.uploadFile("getInput", "ooad/getInput");
//        dockerClientService.runCmd("docker run -it --name getInput java:getInput");
//        List<String> results = dockerClientService.runCmd("aaa");
//
//
//        for (String id: results){
//            System.out.println(id);
//        }
//        System.out.println("-----------------");
//        List<String> ids = dockerClientService.runCmd("docker ps -aqf 'name=getInput'");
//        for (String id: ids){
//            System.out.println(id);
//        }
//        dockerClientService.closeChannel();
//        String id = dockerClient.createContainerCmd("java:getInput").withName("getInput").withStdinOpen(Boolean.TRUE).exec().getId();
//        System.out.println(id);
//        dockerClient.startContainerCmd(id);
//        dockerClientService.runCmd("cd /root/ooad/getInput ; docker build --build-arg file=getInput -t java:getInput .");

//        dockerClientService.runCmd("mkdir /root/jsch-demo");
//        dockerClientService.runCmd("ls /root/jsch-demo");
//        dockerClientService.runCmd("touch /root/jsch-demo/test1; touch /root/jsch-demo/test2");
//        dockerClientService.runCmd("echo 'It a test file.' > /root/jsch-demo/test-file");
//        dockerClientService.runCmd("ls -all /root/jsch-demo");
//        dockerClientService.runCmd("ls -all /root/jsch-demo | grep test");
//        dockerClientService.runCmd("cat /root/jsch-demo/test-file");
//        InspectContainerResponse inspectContainerResponse = dockerClientService.createContainer("getInput", 512, 100L);
//        String source = Objects.requireNonNull(inspectContainerResponse.getMounts()).get(0).getSource();

//        dockerClientService.pushJarToContainer(id,"getInput");
//        dockerClientService.startContainer(inspectContainerResponse.getId());

//        //创建容器
//        CreateContainerResponse container = dockerClientService.createContainer("tomcat",128, (long) 512);
//        //启动容器
//        dockerClientService.startContainer(container.getId());
    }

}
