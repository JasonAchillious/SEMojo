package com.example.v1.semojo.util;

import com.example.v1.semojo.api.model.ContainerModel;
import com.example.v1.semojo.api.model.DockerModel;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerPort;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;
import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectUtil {

    /**
     * 客户端
     */
    public static DockerClient dockerClient;
    /**
     * docker 容器列表缓存
     */
    public static Map<String, ContainerModel> cs = new HashMap<String, ContainerModel>();

    /**
     * docker 基本信息
     */
    public DockerModel dockerModel;

    public static Session session;
    public static Channel channel;
    public static ChannelSftp channelSftp;

    /**
     * 初始化docker链接
     */
    public static DockerClient initClient() {
        if (dockerClient == null) {
            System.out.println("初始化docker连接");
            dockerClient = DockerClientBuilder.getInstance().build();
        }
        refreshContainers();
        return dockerClient;
    }
    /**
     * 删除容器
     *
     */
    public void rmContainer(String containerId) {
        dockerClient.removeContainerCmd(containerId).exec();
        System.out.println("删除容器：" + cs.get(containerId).getName());
        refreshContainers();
    }

    /**
     * 刷新容器列表
     */
    private static void refreshContainers() {
        List<Container> dockerSearch = dockerClient.listContainersCmd().exec();
        if(dockerSearch.size()>0){
            for (Iterator<Container> iterator = dockerSearch.iterator(); iterator.hasNext(); ) {
                Container container = (Container) iterator.next();
                ContainerPort[] containerPort = container.getPorts();
                // 获取容器详细信息
                InspectContainerResponse containerInfo =
                        dockerClient.inspectContainerCmd(container.getId()).exec();
                ContainerModel containerModel = new ContainerModel();
                containerModel.setName(containerInfo.getName());
                containerModel.setId(containerInfo.getId());
                containerModel.setImageName(container.getImage());
                containerModel.setCpuShare(containerInfo.getHostConfig().getCpuShares());
                containerModel.setMemLimit(containerInfo.getHostConfig().getMemory());
                containerModel.setCreateTime(containerInfo.getCreated());
                containerModel.setStatus(containerInfo.getState().getStatus());

                if (!cs.containsKey(container.getId())) {
                    cs.put(container.getId(), containerModel);
                }
            }
        }
    }

    /**
     * 关闭docker连接
     *
     */
    public void closeClient() throws IOException {
        dockerClient.close();
    }

    /**
     * 刷新容器id列表
     */
    public void allContainers() {
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(Boolean.TRUE).exec();
        for (Container container : containers) {
            System.out.println(container.getId());
        }
    }
    /**
     * 启动容器
     *
     */
    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
        refreshContainers();
        System.out.println("启动容器：" + cs.get(containerId).getName());
    }

    /**
     * 停止容器
     *
     */
    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
        System.out.println("停止容器：" + cs.get(containerId).getName());
        refreshContainers();
    }

    /**
     * 获取容器列表
     *
     */
    public List<ContainerModel> getContainers() {
        List<ContainerModel> containerModels = new ArrayList<ContainerModel>();
        for (Iterator<ContainerModel> iterator = cs.values().iterator(); iterator.hasNext(); ) {
            containerModels.add(iterator.next());
        }
        return containerModels;
    }
    /**
     * 获取docker服务基本信息
     */
    public DockerModel getDockerInfo() {

        if (dockerModel == null) {
            dockerModel = new DockerModel();
            Info info = dockerClient.infoCmd().exec();
            System.out.println("info:" + info);
            dockerModel.setTotalMem(info.getMemTotal());
            dockerModel.setImages(info.getImages());
            dockerModel.setCpus(info.getNCPU());
        }
        return dockerModel;
    }

    public static List<String> remoteExecute(Session session, String command) throws JSchException {
        List<String> resultLines = new ArrayList<>();
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            InputStream input = channel.getInputStream();
            channel.connect();
            System.out.println("exec channel connected");
            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
                String inputLine = null;
                while ((inputLine = inputReader.readLine()) != null) {
                    System.out.println(inputLine);
                    resultLines.add(inputLine);
                }
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception e) {
                        System.out.println("JSch inputStream close error:"+e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOcxecption:"+e);
        } finally {
            if (channel != null) {
                try {
                    channel.disconnect();
                } catch (Exception e) {
                    System.out.println("JSch channel disconnect error"+e);
                }
            }

        }
        return resultLines;
    }

    public static List<String> runCmd(String cmd) {
        List<String> resultLines = new ArrayList<>();
        try {
            if (session == null) {
                System.out.println("need create session before cmd");
                return null;
            }
            if (session.isConnected()) {
                System.out.println("Host connected.");
            }
            resultLines = remoteExecute(session, cmd);
        } catch (JSchException e) {
            System.out.println("create sftp channel failed!");
        }
        return resultLines;
    }

    public static void createChannel() {
        try {
            JSch jSch = new JSch();
            session = jSch.getSession("root", "8.210.120.82", 22);
            // 使用用户名密码创建SSH
            session.setPassword("xps11812211`");
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");  // 主动接收ECDSA key fingerprint，不进行HostKeyChecking
            session.setConfig(properties);
            session.setTimeout(0);  // 设置超时时间为无穷大
            session.connect(); // 通过session建立连接

            channel = session.openChannel("sftp"); // 打开SFTP通道
            channel.connect();
            channelSftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            System.out.println("create sftp channel failed!");
        }
    }

    public static void closeChannel() {
        if (channel != null) {
            channel.disconnect();
        }

        if (session != null) {
            session.disconnect();
        }
    }
}
