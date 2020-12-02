package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.IssueResultEnum;
import com.example.v1.semojo.api.enums.ProductResultEnum;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.IssueModel;
import com.example.v1.semojo.api.util.IssueRespResultUtil;
import com.example.v1.semojo.api.util.ProductRespResultUtil;
import com.example.v1.semojo.api.util.UserRespResultUtil;
import com.example.v1.semojo.entities.Issue;
import com.example.v1.semojo.services.IssueService;
import com.example.v1.semojo.services.ProductService;
import com.example.v1.semojo.services.UserService;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IssueController {
    @Autowired
    IssueService issueService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/product/{productId}/issues")
    public WebRespResult getIssues(@PathVariable Long productId){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }
        List<IssueModel> issueModels =  issueService.findIssuesByProductId(productId);
        return new WebRespResult(200, "getIssues success", issueModels);
    }

    @PostMapping("customer/{username}/product/{productId}/issue")
    public WebRespResult addIssue(@PathVariable String username,
                                  @PathVariable Long productId,
                                  @RequestParam String title,
                                  @RequestParam String outline,
                                  @RequestParam String context){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else if (userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }
        Issue issue = issueService.addNewIssue(username, productId, title, outline, context);
        return new WebRespResult(200, "success", new IssueModel(issue));
    }

    @PutMapping("customer/{username}/product/{productId}/issue/{issueId}")
    public WebRespResult updateIssue(@PathVariable String username,
                                     @PathVariable Long productId,
                                     @PathVariable Long issueId,
                                     @RequestParam String title,
                                     @RequestParam String outline,
                                     @RequestParam String context){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else if (userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (issueService.findIssueByIssueId(issueId)==null){
            return IssueRespResultUtil.error(IssueResultEnum.ISSUE_NOT_EXIST.getCode(), IssueResultEnum.ISSUE_NOT_EXIST.getMsg());
        }
        IssueModel issueModel = issueService.updateIssue(issueId, title, outline, context);
        return new WebRespResult(200, "success", issueModel);
    }

    @DeleteMapping("contributor/{username}/product/{productId}/issue/{issueId}")
    public WebRespResult deleteIssue(@PathVariable String username,
                                     @PathVariable Long productId,
                                     @PathVariable Long issueId){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else if (userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (issueService.findIssueByIssueId(issueId)==null){
            return IssueRespResultUtil.error(IssueResultEnum.ISSUE_NOT_EXIST.getCode(), IssueResultEnum.ISSUE_NOT_EXIST.getMsg());
        }
        issueService.deleteIssue(username, productId, issueId);
        return new WebRespResult(200, "success");
    }

    @GetMapping("/product/{productId}/issue/{issueId}/sub_issues")
    public WebRespResult getSubIssues(@PathVariable Long productId, @PathVariable Long issueId){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else if (issueService.findIssueByIssueId(issueId)==null){
            return IssueRespResultUtil.error(IssueResultEnum.ISSUE_NOT_EXIST.getCode(), IssueResultEnum.ISSUE_NOT_EXIST.getMsg());
        }
        return new WebRespResult(200, "success", issueService.getSubIssues(issueId));
    }

    @PostMapping("/customer/{username}/product/{productId}/issue/{issueId}/sub_issue")
    public WebRespResult addSubIssues(@PathVariable String username,
                                      @PathVariable Long productId,
                                      @PathVariable Long issueId,
                                      @RequestParam String answerToWho,
                                      @RequestParam String context){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else if (userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (issueService.findIssueByIssueId(issueId)==null){
            return IssueRespResultUtil.error(IssueResultEnum.ISSUE_NOT_EXIST.getCode(), IssueResultEnum.ISSUE_NOT_EXIST.getMsg());
        }
        return new WebRespResult(200, "success", issueService.addSubIssues(username, issueId, answerToWho, context));
    }

    @PutMapping("/customer/{username}/product/{productId}/issue/{issueId}/sub_issue/{subIssueId}")
    public WebRespResult updateSubIssues(@PathVariable String username,
                                         @PathVariable Long productId,
                                         @PathVariable Long issueId,
                                         @PathVariable Long subIssueId,
                                         @RequestParam String context,
                                         @RequestParam String status){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else if (userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (issueService.findIssueByIssueId(issueId)==null){
            return IssueRespResultUtil.error(IssueResultEnum.ISSUE_NOT_EXIST.getCode(), IssueResultEnum.ISSUE_NOT_EXIST.getMsg());
        }else if (issueService.findSubIssueBySubIssueId(subIssueId) == null){
            return IssueRespResultUtil.error(IssueResultEnum.SUBISSUE_NOT_EXIST.getCode(), IssueResultEnum.SUBISSUE_NOT_EXIST.getMsg());
        }else if (issueService.findSubIssueBySubIssueId(subIssueId).getSubIssuer() != userService.findUserByUsername(username)){
            return UserRespResultUtil.error(IssueResultEnum.NO_AUTHORITY.getCode(), IssueResultEnum.NO_AUTHORITY.getMsg());
        }
        return new WebRespResult(200, "success", issueService.updateSubIssues(subIssueId,context,status));
    }

    @DeleteMapping("/admin/{username}/product/{productId}/issue/{issueId}/sub_issue/{subIssueId}")
    public WebRespResult deleteSubIssues(@PathVariable String username,
                                         @PathVariable Long productId,
                                         @PathVariable Long issueId,
                                         @PathVariable Long subIssueId){
        if (productService.findProductByProductId(productId)==null){
            return ProductRespResultUtil.error(ProductResultEnum.PRODUCT_NOT_EXIST.getCode(), ProductResultEnum.PRODUCT_NOT_EXIST.getMsg());
        }else if (userService.findUserByUsername(username)==null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else if (issueService.findIssueByIssueId(issueId)==null){
            return IssueRespResultUtil.error(IssueResultEnum.ISSUE_NOT_EXIST.getCode(), IssueResultEnum.ISSUE_NOT_EXIST.getMsg());
        }else if (issueService.findSubIssueBySubIssueId(subIssueId) == null){
            return IssueRespResultUtil.error(IssueResultEnum.SUBISSUE_NOT_EXIST.getCode(), IssueResultEnum.SUBISSUE_NOT_EXIST.getMsg());
        }else if (issueService.findSubIssueBySubIssueId(subIssueId).getSubIssuer() != userService.findUserByUsername(username)){
            return UserRespResultUtil.error(IssueResultEnum.NO_AUTHORITY.getCode(), IssueResultEnum.NO_AUTHORITY.getMsg());
        }
        issueService.deleteSubIssue(username, issueId, subIssueId);
        return new WebRespResult(200, "success");
    }

}
