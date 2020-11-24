package com.example.v1.semojo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
    private String context;
    private Integer score;
    private Timestamp createTime;
    private Timestamp updateTime;

    public static enum ReviewStatus{
        hidden,
        normal,
        deprecated
    }
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    private User reviewer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product reviewProduct;
    @OneToMany(mappedBy = "answerReview", cascade = CascadeType.ALL)
    private List<subReview> subReviewList;

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public List<subReview> getSubReviewList() {
        return subReviewList;
    }

    public void setSubReviewList(List<subReview> subReviewList) {
        this.subReviewList = subReviewList;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
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

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Product getReviewProduct() {
        return reviewProduct;
    }

    public void setReviewProduct(Product reviewProduct) {
        this.reviewProduct = reviewProduct;
    }
}
