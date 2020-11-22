package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentDao extends JpaRepository<Document, Long> {
}
