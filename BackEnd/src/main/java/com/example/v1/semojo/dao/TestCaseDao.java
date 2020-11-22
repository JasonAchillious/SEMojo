package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseDao extends JpaRepository<TestCase, Long> {
}
