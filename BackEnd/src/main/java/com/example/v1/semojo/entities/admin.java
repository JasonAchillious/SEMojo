package com.example.v1.semojo.entities;

import javax.persistence.*;

@Entity
public class admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;

//    admin_id INT NOT NULL,
//    account INT NOT NULL,
//    password INT NOT NULL,
//    name VARCHAR(45) NOT NULL,
//    PRIMARY KEY (admin_id));
}
