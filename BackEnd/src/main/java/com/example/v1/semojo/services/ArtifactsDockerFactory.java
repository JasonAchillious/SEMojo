package com.example.v1.semojo.services;

import com.jcraft.jsch.ChannelSftp;

import java.io.File;

public interface ArtifactsDockerFactory {
    public void createImage(String filename);
    public void copyFile(String filename, String containerId);
    public boolean uploadFile(String filename);
    public boolean getFile(String filename);
}
