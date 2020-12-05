package com.example.v1.semojo.dao;

import com.example.v1.semojo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<Review, Long> {
    Review findReviewByReviewId(long reviewId);
}
