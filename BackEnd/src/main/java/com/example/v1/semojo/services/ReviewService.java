package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.ReviewModel;
import com.example.v1.semojo.api.model.SubReviewModel;
import com.example.v1.semojo.dao.*;
import com.example.v1.semojo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewDao reviewDao;
    @Autowired
    SubReviewDao subReviewDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserAuthDao userAuthDao;

    public Review findReviewByReviewId(long reviewId){
        return reviewDao.findReviewByReviewId(reviewId);
    }

    public List<ReviewModel> findReviewsByAdminUsername(String adminUsername)
    {
        User user = userAuthDao.findUserAuthByUsername(adminUsername).getUser();
        List<Review> reviews;
        List<ReviewModel> reviewModels = new ArrayList<>();
        if (user.getReviews()!= null){
            reviews = user.getReviews();
        }else {
            reviews = new ArrayList<>();
        }
        for (Review review: reviews){
            reviewModels.add(new ReviewModel(review));
        }
        return reviewModels;
    }

    public Review addNewReview(String username, String adminUsername, String title, String outline, String context){
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        User admin = userAuthDao.findUserAuthByUsername(adminUsername).getUser();
        Review review = new Review();
        review.setTitle(title);
        review.setOutline(outline);
        review.setContext(context);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        review.setCreateTime(d);
        review.setUpdateTime(d);
        review.setReviewer(user);
        review.setReviewAdmin(admin);
        review.setStatus(Review.ReviewStatus.normal);
        reviewDao.save(review);
        List<Review> userReview;
        if (user.getReviews()!=null){
            userReview = user.getReviews();
        }else {
            userReview = new ArrayList<>();
        }
        userReview.add(review);
        user.setReviews(userReview);
        userDao.save(user);
        List<Review> adminReview;
        if (admin.getReviewList()!=null){
            adminReview = admin.getReviewList();
        }else {
            adminReview = new ArrayList<>();
        }
        adminReview.add(review);
        admin.setReviewList(adminReview);
        userDao.save(admin);
        return reviewDao.save(review);
    }

    public SubReview findSubReviewBySubReviewId(long subReviewId){
        return subReviewDao.findSubReviewBySubReviewId(subReviewId);
    }

    public ReviewModel updateReview(long reviewId, String title, String outline, String context, String state){
        Review review = reviewDao.findReviewByReviewId(reviewId);
        review.setTitle(title);
        review.setOutline(outline);
        review.setContext(context);
        review.setStatus(Review.ReviewStatus.valueOf(state));
        return new ReviewModel(reviewDao.save(review));
    }

    public void deleteReview(String username, String adminUsername, long reviewId){
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        User admin = userAuthDao.findUserAuthByUsername(adminUsername).getUser();
        Review review = reviewDao.findReviewByReviewId(reviewId);
        List<Review> userReviews = user.getReviews();
        List<Review> adminReviews = admin.getReviewList();
        userReviews.remove(review);
        adminReviews.remove(review);
        userDao.save(user);
        userDao.save(admin);
        reviewDao.delete(review);
    }

    public List<SubReviewModel> getSubReviews(long reviewId){
        List<SubReview> subReviews;
        List<SubReviewModel> subReviewModels = new ArrayList<>();
        Review review = reviewDao.findReviewByReviewId(reviewId);
        if (review.getSubReviewList()!=null){
            subReviews = review.getSubReviewList();
        }else {
            subReviews = new ArrayList<>();
        }
        for (int i = 0;i < subReviews.size();i++){
            subReviewModels.add(new SubReviewModel(subReviews.get(i)));
        }
        return subReviewModels;
    }

    public SubReviewModel addSubReview(String username, long reviewId, String answerToWho, String context){
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        Review review = reviewDao.findReviewByReviewId(reviewId);
        SubReview subReview = new SubReview();
        subReview.setAnswerReview(review);
        subReview.setAnswerToWho(answerToWho);
        subReview.setContext(context);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        subReview.setCreateTime(d);
        subReview.setUpdateTime(d);
        subReview.setPoster(user);
        subReview.setStatus(Review.ReviewStatus.normal);
        subReviewDao.save(subReview);
        List<SubReview> UserSubReviews;
        if (user.getSubIssues() != null){
            UserSubReviews = user.getSubReviews();
        }else {
            UserSubReviews = new ArrayList<>();
        }
        UserSubReviews.add(subReview);
        user.setSubReviews(UserSubReviews);
        userDao.save(user);
        List<SubReview> reviewSubReviews;
        if (review.getSubReviewList() != null){
            reviewSubReviews = review.getSubReviewList();
        }else {
            reviewSubReviews = new ArrayList<>();
        }
        reviewSubReviews.add(subReview);
        review.setSubReviewList(reviewSubReviews);
        reviewDao.save(review);
        return new SubReviewModel(subReview);
    }

    public SubReviewModel updateSubReview(long subReviewId, String context, String status){
        SubReview subReview = subReviewDao.findSubReviewBySubReviewId(subReviewId);
        subReview.setContext(context);
        subReview.setStatus(Review.ReviewStatus.valueOf(status));
        Timestamp d = new Timestamp(System.currentTimeMillis());
        subReview.setUpdateTime(d);
        return new SubReviewModel(subReviewDao.save(subReview));
    }

    public void deleteSubReview(long reviewId, long subReviewId){
        Review review = reviewDao.findReviewByReviewId(reviewId);
        SubReview subReview = subReviewDao.findSubReviewBySubReviewId(subReviewId);
        User user = subReview.getPoster();
        List<SubReview> userSubReview = user.getSubReviews();
        List<SubReview> reviewSubReview = review.getSubReviewList();
        userSubReview.remove(subReview);
        reviewSubReview.remove(subReview);
        review.setSubReviewList(reviewSubReview);
        user.setSubReviews(userSubReview);
        userDao.save(user);
        reviewDao.save(review);
        subReviewDao.delete(subReview);
    }
}
