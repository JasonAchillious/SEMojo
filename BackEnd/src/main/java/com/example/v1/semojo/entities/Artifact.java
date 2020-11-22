package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "artifact")
public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ARTIFACT_ID")
    private long id;
    private String uploaderName;
    private String description;
    private String status;
    private Timestamp upload_time;
    private Timestamp update_time;
}