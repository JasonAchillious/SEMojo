package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.Issue;
import com.example.v1.semojo.entities.Review;
import com.example.v1.semojo.entities.SubIssue;
import com.example.v1.semojo.entities.User;

import java.sql.Timestamp;

public class SubIssueModel {
    private long subIssueId;
    private String answerToWho;
    private String context;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Issue.IssueStatus status;
    private String issue;
    private String poster;

    public SubIssueModel(SubIssue subIssue) {
        this.subIssueId = subIssue.getSubIssueId();
        this.answerToWho = subIssue.getAnswerToWho();
        this.context = subIssue.getContext();
        this.createTime = subIssue.getCreateTime();
        this.updateTime = subIssue.getUpdateTime();
        this.status = subIssue.getStatus();
        this.issue = subIssue.getIssue().getTitle();
        this.poster = subIssue.getPoster().getAuth().getUsername();
    }

    public void setStatus(Issue.IssueStatus status) {
        this.status = status;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
