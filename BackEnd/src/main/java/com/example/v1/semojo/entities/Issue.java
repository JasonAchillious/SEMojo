package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long issueId;
    private String title;
    private String outline;
    private String context;
    private Timestamp createTime;
    private Timestamp updateTime;

    public static enum IssueStatus{
        open,
        close,
        duplicated
    }
    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User questioner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product issueProduct;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private List<SubIssue> subIssueList;

    public List<SubIssue> getSubIssueList() {
        return subIssueList;
    }

    public void setSubIssueList(List<SubIssue> subIssueList) {
        this.subIssueList = subIssueList;
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

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public User getQuestioner() {
        return questioner;
    }

    public void setQuestioner(User questioner) {
        this.questioner = questioner;
    }

    public Product getIssueProduct() {
        return issueProduct;
    }

    public void setIssueProduct(Product issueProduct) {
        this.issueProduct = issueProduct;
    }
}
