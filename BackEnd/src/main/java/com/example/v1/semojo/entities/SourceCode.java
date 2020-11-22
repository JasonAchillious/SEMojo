package com.example.v1.semojo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity(name = "source_code")
public class SourceCode {
    @Id
    private long id;
    private String uploader;
    private String description;
    private Timestamp uploadTime;
    private String status;


    private String lang;
}
