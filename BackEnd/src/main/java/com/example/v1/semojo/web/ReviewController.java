package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.IssueResultEnum;
import com.example.v1.semojo.api.enums.ReviewResultEnum;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.ReviewModel;
import com.example.v1.semojo.api.model.SubReviewModel;
import com.example.v1.semojo.api.util.ReviewRespResultUtil;
import com.example.v1.semojo.api.util.UserRespResultUtil;
import com.example.v1.semojo.entities.Review;
import com.example.v1.semojo.services.ReviewService;
import com.example.v1.semojo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    UserService userService;

    @GetMapping("/review/admin/{adminUsername}")
    public WebRespResult getReview(@PathVariable String adminUsername){
        if (userService.findUserByUsername(adminUsername)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }
        List<ReviewModel> reviewModels = reviewService.findReviewsByAdminUsername(adminUsername);
        return new WebRespResult(200, "success", reviewModels);
    }

    @PostMapping("/customer/{username}/admin/{adminUsername}/review")
    public WebRespResult addReview(@PathVariable String username,
                                   @PathVariable String adminUsername,
                                   @RequestParam String title,
                                   @RequestParam String outline,
                                   @RequestParam String context){
        if (userService.findUserByUsername(adminUsername)==null||userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }
        Review review = reviewService.addNewReview(username, adminUsername, title, outline, context);
        return new WebRespResult(200, "success", new ReviewModel(review));
    }

    @PutMapping("/customer/{username}/admin/{adminUsername}/review/{reviewId}")
    public WebRespResult updateReview(@PathVariable String username,
                                      @PathVariable String adminUsername,
                                      @PathVariable long reviewId,
                                      @RequestParam String title,
                                      @RequestParam String outline,
                                      @RequestParam String context,
                                      @RequestParam String status){
        if (userService.findUserByUsername(adminUsername)==null||userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId)==null){
            return ReviewRespResultUtil.error(ReviewResultEnum.REVIEW_NOT_EXIST.getCode(), ReviewResultEnum.REVIEW_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId).getReviewer() != userService.findUserByUsername(username)){
            return UserRespResultUtil.error(IssueResultEnum.NO_AUTHORITY.getCode(), IssueResultEnum.NO_AUTHORITY.getMsg());
        }
        ReviewModel reviewModel = reviewService.updateReview(reviewId, title, outline, context, status);
        return new WebRespResult(200, "success", reviewModel);
    }

    @DeleteMapping("/admin/{adminUsername}/customer/{username}/review/{reviewId}")
    public WebRespResult deleteReview(@PathVariable String adminUsername,
                                      @PathVariable String username,
                                      @PathVariable long reviewId){
        if (userService.findUserByUsername(adminUsername)==null||userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId)==null){
            return ReviewRespResultUtil.error(ReviewResultEnum.REVIEW_NOT_EXIST.getCode(), ReviewResultEnum.REVIEW_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId).getReviewer() != userService.findUserByUsername(username)){
            return UserRespResultUtil.error(IssueResultEnum.NO_AUTHORITY.getCode(), IssueResultEnum.NO_AUTHORITY.getMsg());
        }
        reviewService.deleteReview(username, adminUsername, reviewId);
        return new WebRespResult(200, "success");
    }

    @GetMapping("/review/{reviewId}/admin/{adminUsername}/subReviews")
    public WebRespResult getSubReviews(@PathVariable String adminUsername, @PathVariable long reviewId){
        if (userService.findUserByUsername(adminUsername)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId)==null){
            return ReviewRespResultUtil.error(ReviewResultEnum.REVIEW_NOT_EXIST.getCode(), ReviewResultEnum.REVIEW_NOT_EXIST.getMsg());
        }
        List<SubReviewModel> subIssueModels = reviewService.getSubReviews(reviewId);
        return new WebRespResult(200, "success", subIssueModels);
    }

    @PostMapping("/customer/{username}/admin/{adminUsername}/review/{reviewId}/subReview")
    public WebRespResult addSubReview(@PathVariable String username,
                                       @PathVariable String adminUsername,
                                       @PathVariable long reviewId,
                                       @RequestParam String answerToWho,
                                       @RequestParam String context){
        if (userService.findUserByUsername(adminUsername)==null||userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId)==null){
            return ReviewRespResultUtil.error(ReviewResultEnum.REVIEW_NOT_EXIST.getCode(), ReviewResultEnum.REVIEW_NOT_EXIST.getMsg());
        }
        SubReviewModel subReviewModel = reviewService.addSubReview(username, reviewId, answerToWho, context);
        return new WebRespResult(200, "success", subReviewModel);
    }

    @PutMapping("/customer/{username}/admin/{adminUsername}/review/{reviewId}/subReview/{subReviewId}")
    public WebRespResult updateSubReview(@PathVariable String username,
                                         @PathVariable String adminUsername,
                                         @PathVariable long reviewId,
                                         @PathVariable long subReviewId,
                                         @RequestParam String context,
                                         @RequestParam String status){
        if (userService.findUserByUsername(adminUsername)==null||userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId)==null){
            return ReviewRespResultUtil.error(ReviewResultEnum.REVIEW_NOT_EXIST.getCode(), ReviewResultEnum.REVIEW_NOT_EXIST.getMsg());
        }else if (reviewService.findSubReviewBySubReviewId(subReviewId) == null){
            return ReviewRespResultUtil.error(ReviewResultEnum.SUBREVIEW_NOT_EXIST.getCode(), ReviewResultEnum.SUBREVIEW_NOT_EXIST.getMsg());
        }else if (reviewService.findSubReviewBySubReviewId(subReviewId).getPoster() != userService.findUserByUsername(username)){
            return UserRespResultUtil.error(IssueResultEnum.NO_AUTHORITY.getCode(), IssueResultEnum.NO_AUTHORITY.getMsg());
        }
        SubReviewModel subReviewModel = reviewService.updateSubReview(subReviewId, context, status);
        return new WebRespResult(200, "success", subReviewModel);
    }

    @DeleteMapping("/admin/{adminUsername}/customer/{username}/review/{reviewId}/subReview/{subReviewId}")
    public WebRespResult deleteSubReview(@PathVariable String adminUsername,
                                         @PathVariable String username,
                                         @PathVariable long reviewId,
                                         @PathVariable long subReviewId){
        if (userService.findUserByUsername(adminUsername)==null||userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (reviewService.findReviewByReviewId(reviewId)==null){
            return ReviewRespResultUtil.error(ReviewResultEnum.REVIEW_NOT_EXIST.getCode(), ReviewResultEnum.REVIEW_NOT_EXIST.getMsg());
        }else if (reviewService.findSubReviewBySubReviewId(subReviewId) == null){
            return ReviewRespResultUtil.error(ReviewResultEnum.SUBREVIEW_NOT_EXIST.getCode(), ReviewResultEnum.SUBREVIEW_NOT_EXIST.getMsg());
        }
        reviewService.deleteSubReview(reviewId, subReviewId);
        return new WebRespResult(200, "success");
    }
}
