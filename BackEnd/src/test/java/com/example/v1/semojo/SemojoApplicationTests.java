package com.example.v1.semojo;

import com.example.v1.semojo.api.model.ContainerModel;
import com.example.v1.semojo.api.model.DockerModel;
import com.example.v1.semojo.services.DockerClientService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SemojoApplicationTests {
@Autowired
DockerClientService dockerClientService;
    @Test
    void contextLoads() {
        //连接docker服务器
        DockerClient client = dockerClientService.initClient();
        DockerModel dockerModel = dockerClientService.getDockerInfo();
        List<ContainerModel> containerModels = dockerClientService.getContainers();
        System.out.println(containerModels.toString());
        System.out.println(dockerModel.toString());

//        //创建容器
//        CreateContainerResponse container = dockerClientService.createContainer("tomcat",128, (long) 512);
//        //启动容器
//        dockerClientService.startContainer(container.getId());
    }

}
