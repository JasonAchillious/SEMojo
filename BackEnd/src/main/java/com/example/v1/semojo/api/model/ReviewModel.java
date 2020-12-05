package com.example.v1.semojo.api.model;

import com.example.v1.semojo.entities.Review;
import com.example.v1.semojo.entities.SubReview;
import com.example.v1.semojo.entities.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReviewModel {
    private Long reviewId;
    private String title;
    private String outline;
    private String context;
    private Double score;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Review.ReviewStatus status;
    private String reviewer;
    private String reviewAdmin;
    private List<String> subReviewList;

    public ReviewModel(Review review) {
        this.reviewId = review.getReviewId();
        this.title = review.getTitle();
        this.outline = review.getOutline();
        this.context = review.getContext();
        this.score = review.getScore();
        this.createTime = review.getCreateTime();
        this.updateTime = review.getUpdateTime();
        this.status = review.getStatus();
        this.reviewer = review.getReviewer().getAuth().getUsername();
        this.reviewAdmin = review.getReviewAdmin().getAuth().getUsername();
        List<String> subReviewList = new ArrayList<>();
        if (review.getSubReviewList()!= null){
            for (SubReview subReview : review.getSubReviewList()){
                subReviewList.add(subReview.getContext());
            }
        }
        this.subReviewList = subReviewList;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewAdmin() {
        return reviewAdmin;
    }

    public void setReviewAdmin(String reviewAdmin) {
        this.reviewAdmin = reviewAdmin;
    }

    public List<String> getSubReviewList() {
        return subReviewList;
    }

    public void setSubReviewList(List<String> subReviewList) {
        this.subReviewList = subReviewList;
    }
}
