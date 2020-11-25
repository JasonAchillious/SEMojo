package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "sub_review")
public class subReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String answerToWho;
    private String context;
    private Timestamp createTime;
    private Timestamp updateTime;
    @Enumerated(EnumType.STRING)
    private Review.ReviewStatus status;

    @ManyToOne
    private Review answerReview;

    public Review.ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(Review.ReviewStatus status) {
        this.status = status;
    }

    public Review getAnswerReview() {
        return answerReview;
    }

    public void setAnswerReview(Review answerReview) {
        this.answerReview = answerReview;
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

}
