package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.Review;
import com.example.v1.semojo.entities.SubReview;
import com.example.v1.semojo.entities.User;

import java.sql.Timestamp;

public class SubReviewModel {
    private long subReviewId;
    private String answerToWho;
    private String context;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Review.ReviewStatus status;
    private String answerReview;
    private String poster;

    public SubReviewModel(SubReview subReview) {
        this.subReviewId = subReview.getSubReviewId();
        this.answerToWho = subReview.getAnswerToWho();
        this.context = subReview.getContext();
        this.createTime = subReview.getCreateTime();
        this.updateTime = subReview.getUpdateTime();
        this.status = subReview.getStatus();
        this.answerReview = subReview.getAnswerReview().getTitle();
        this.poster = subReview.getPoster().getAuth().getUsername();
    }

    public long getSubReviewId() {
        return subReviewId;
    }

    public void setSubReviewId(long subReviewId) {
        this.subReviewId = subReviewId;
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

    public Review.ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(Review.ReviewStatus status) {
        this.status = status;
    }

    public String getAnswerReview() {
        return answerReview;
    }

    public void setAnswerReview(String answerReview) {
        this.answerReview = answerReview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
