package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "sub_issue")
public class SubIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subIssueId;
    private String answerToWho;
    private String context;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Enumerated(EnumType.STRING)
    private Issue.IssueStatus status;

    @ManyToOne
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    private User subIssuer;

    public User getSubIssuer() {
        return subIssuer;
    }

    public void setSubIssuer(User subIssuer) {
        this.subIssuer = subIssuer;
    }

    public long getSubIssueId() {
        return subIssueId;
    }

    public void setSubIssueId(long subIssueId) {
        this.subIssueId = subIssueId;
    }

    public String getAnswerToWho() {
        return answerToWho;
    }

    public void setAnswerToWho(String answerToWho) {
        this.answerToWho = answerToWho;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Issue.IssueStatus getStatus() {
        return status;
    }

    public void setStatus(Issue.IssueStatus status) {
        this.status = status;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
