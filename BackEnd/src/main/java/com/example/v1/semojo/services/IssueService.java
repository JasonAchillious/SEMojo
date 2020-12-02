package com.example.v1.semojo.services;

import com.example.v1.semojo.api.model.IssueModel;
import com.example.v1.semojo.api.model.SubIssueModel;
import com.example.v1.semojo.dao.*;
import com.example.v1.semojo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    IssueDao issueDao;
    @Autowired
    SubIssueDao subIssueDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserAuthDao userAuthDao;

    public List<IssueModel> findIssuesByProductId(long productId){
        Product product = productDao.findProductByProductId(productId);
        List<IssueModel> issueModels = new ArrayList<>();
        List<Issue> issues;
        if (product.getIssueList()!=null){
            issues = product.getIssueList();
        }else {
            issues = new ArrayList<>();
        }
        for (Issue issue: issues){
            issueModels.add(new IssueModel(issue));
        }
        return issueModels;
    }

    public Issue findIssueByIssueId(long IssueId){
        return issueDao.findIssueByIssueId(IssueId);
    }
    
    public Issue addNewIssue( String username, Long productId, String title, String outline,
                              String context){
        Issue issue = new Issue();
        issue.setContext(context);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        issue.setCreateTime(d);
        issue.setOutline(outline);
        issue.setStatus(Issue.IssueStatus.open);
        List<SubIssue> subIssues = new ArrayList<>();
        issue.setSubIssueList(subIssues);
        issue.setTitle(title);
        issue.setUpdateTime(d);
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        List<Issue> userIssues;
        if (user.getIssues()!=null){
            userIssues = user.getIssues();
        }else {
            userIssues = new ArrayList<>();
        }
        userIssues.add(issue);
        user.setIssues(userIssues);
        issue.setQuestioner(user);
        userDao.save(user);

        Product product = productDao.findProductByProductId(productId);
        List<Issue> issueList;
        if (product.getIssueList()!=null){
            issueList = product.getIssueList();
        }else {
            issueList = new ArrayList<>();
        }
        issueList.add(issue);
        product.setIssueList(issueList);
        issue.setIssueProduct(product);
        productDao.save(product);
        return issueDao.save(issue);

    }

    public SubIssue findSubIssueBySubIssueId(long subIssueId){
        return subIssueDao.findSubIssueBySubIssueId(subIssueId);
    }

    public IssueModel updateIssue(long issueId, String title, String outline, String context){
        Issue issue = issueDao.findIssueByIssueId(issueId);
        issue.setTitle(title);
        issue.setOutline(outline);
        issue.setContext(context);
        issue.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        issueDao.save(issue);
        return new IssueModel(issue);
    }

    public void deleteIssue(String username, long productId, long issueId){
        Issue issue = issueDao.findIssueByIssueId(issueId);
        Product product = productDao.findProductByProductId(productId);
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        List<Issue> userIssueList = user.getIssues();
        userIssueList.remove(issue);
        List<Issue> productIssueList = product.getIssueList();
        productIssueList.remove(issue);
        productDao.save(product);
        userDao.save(user);
        issueDao.delete(issue);
    }

    public List<SubIssueModel> getSubIssues(long issueId){
        List<SubIssue> subIssues;
        List<SubIssueModel> subIssueModels = new ArrayList<>();
        Issue issue = issueDao.findIssueByIssueId(issueId);
        if (issue.getSubIssueList() != null){
            subIssues = issue.getSubIssueList();
        }else {
            subIssues = new ArrayList<>();
        }
        for (int i = 0; i < subIssues.size();i++){
            subIssueModels.add(new SubIssueModel(subIssues.get(i)));
        }
        return subIssueModels;
    }

    public SubIssueModel addSubIssues(String username, long issueId, String answerToWho, String context){
        List<SubIssue> subIssues;
        Issue issue = issueDao.findIssueByIssueId(issueId);
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        if (issue.getSubIssueList() != null){
            subIssues = issue.getSubIssueList();
        }else {
            subIssues = new ArrayList<>();
        }
        SubIssue subIssue = new SubIssue();
        subIssue.setIssue(issue);
        subIssue.setAnswerToWho(answerToWho);
        subIssue.setContext(context);
        subIssue.setSubIssuer(user);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        subIssue.setCreateTime(d);
        subIssue.setUpdateTime(d);
        subIssue.setStatus(Issue.IssueStatus.open);
        subIssues.add(subIssue);
        subIssueDao.save(subIssue);
        issue.setSubIssueList(subIssues);
        List<SubIssue> subIssueList;
        if (user.getSubIssues() != null){
            subIssueList = user.getSubIssues();
        }else {
            subIssueList = new ArrayList<>();
        }
        subIssueList.add(subIssue);
        user.setSubIssues(subIssueList);
        userDao.save(user);
        issueDao.save(issue);
        return new SubIssueModel(subIssue);
    }

    public SubIssueModel updateSubIssues(long subIssueId, String context, String status){
        SubIssue subIssue = subIssueDao.findSubIssueBySubIssueId(subIssueId);
        subIssue.setContext(context);
        subIssue.setStatus(Issue.IssueStatus.valueOf(status));
        return new SubIssueModel(subIssue);
    }

    public void deleteSubIssue(String username, long issueId, long subIssueId){
        Issue issue = issueDao.findIssueByIssueId(issueId);
        User user = userAuthDao.findUserAuthByUsername(username).getUser();
        SubIssue subIssue = subIssueDao.findSubIssueBySubIssueId(subIssueId);
        List<SubIssue> subIssues = issue.getSubIssueList();
        subIssues.remove(subIssue);
        List<SubIssue> UserSubIssues = user.getSubIssues();
        UserSubIssues.remove(subIssue);
        subIssueDao.delete(subIssue);
        issueDao.save(issue);
        userDao.save(user);
    }
}
