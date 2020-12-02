package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueDao extends JpaRepository<Issue, Long> {
    Issue findIssueByIssueId(long id);
}
