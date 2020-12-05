package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.SubReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubReviewDao extends JpaRepository<SubReview, Long> {
    SubReview findSubReviewBySubReviewId(long subReviewId);
}
