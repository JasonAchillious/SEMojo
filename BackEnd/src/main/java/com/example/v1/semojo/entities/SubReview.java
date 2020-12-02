package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "sub_review")
public class SubReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subReviewId;
    private String answerToWho;
    private String context;
    private Timestamp createTime;
    private Timestamp updateTime;
    @Enumerated(EnumType.STRING)
    private Review.ReviewStatus status;

    @ManyToOne
    private Review answerReview;

    @ManyToOne(fetch = FetchType.LAZY)
    private User poster;

    public long getSubReviewId() {
        return subReviewId;
    }

    public void setSubReviewId(long subReviewId) {
        this.subReviewId = subReviewId;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

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
