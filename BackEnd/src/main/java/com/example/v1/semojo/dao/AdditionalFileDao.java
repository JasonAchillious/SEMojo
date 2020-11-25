package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.AdditionalFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalFileDao extends JpaRepository<AdditionalFile, Long> {
    AdditionalFile findAdditionalFileById(Long id);
}
