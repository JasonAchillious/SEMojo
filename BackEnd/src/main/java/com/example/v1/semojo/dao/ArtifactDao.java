package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactDao extends JpaRepository<Artifact, Long> {

}
