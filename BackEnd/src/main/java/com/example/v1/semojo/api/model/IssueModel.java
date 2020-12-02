package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.Issue;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.SubIssue;
import com.example.v1.semojo.entities.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IssueModel {
    private Long issueId;
    private String title;
    private String outline;
    private String context;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Issue.IssueStatus status;
    private String questioner;
    private String issueProduct;
    private List<String> subIssueList;

    public IssueModel(Issue issue) {
        this.issueId = issue.getIssueId();
        this.title = issue.getTitle();
        this.outline = issue.getOutline();
        this.context = issue.getContext();
        this.createTime = issue.getCreateTime();
        this.updateTime = issue.getUpdateTime();
        this.status = issue.getStatus();
        this.questioner = issue.getQuestioner().getAuth().getUsername();
        this.issueProduct = issue.getIssueProduct().getProductName();
        List<String> subIssues = new ArrayList<>();
        if (issue.getSubIssueList()!=null){
            for (SubIssue subIssue: issue.getSubIssueList()){
                subIssues.add(subIssue.getContext());
            }
        }
        this.subIssueList = subIssues;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
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

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    public String getIssueProduct() {
        return issueProduct;
    }

    public void setIssueProduct(String issueProduct) {
        this.issueProduct = issueProduct;
    }

    public List<String> getSubIssueList() {
        return subIssueList;
    }

    public void setSubIssueList(List<String> subIssueList) {
        this.subIssueList = subIssueList;
    }
}
