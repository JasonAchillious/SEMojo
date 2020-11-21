package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "technology")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contributor;
    private String outline;
    private int lang;
    private int review_star;
    private int sales_volume;
    private String status;
    private Timestamp create_time;
    private Timestamp update_time;
}