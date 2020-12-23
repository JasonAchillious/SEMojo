package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.UserAllInfoModel;
import com.example.v1.semojo.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;
    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping("/search/user")
    public WebRespResult searchUserByName(@RequestParam String username){
        List<UserAllInfoModel> userList = searchService.searchUserNameLike(username);
        return new WebRespResult<List>(200, "success", userList);
    }

    @GetMapping("search/product")
    public WebRespResult searchProduct(@RequestParam String keyword){

        return new WebRespResult(200, "success");
    }

    @GetMapping("search/code")
    public WebRespResult searchCode(@RequestParam String keyword){

        return new WebRespResult(200, "success");
    }
}
