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
import java.util.Map;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;


    @GetMapping("/search/user")
    public WebRespResult searchUserByName(@RequestParam String username){
        try {
            List<UserAllInfoModel> userList = searchService.searchUserNameLike(username);
            return new WebRespResult<List>(200, "success", userList);
        } catch (Exception e){
            return new WebRespResult(500, "error");
        }
    }

    @GetMapping("search/product")
    public WebRespResult searchProduct(@RequestParam String keyword){
        try {
            Map<String, List> searchResult = searchService.searchProduct(keyword);
            return new WebRespResult<Map>(200, "success", searchResult);
        } catch (Exception e){
            return new WebRespResult(500, "error");
        }
    }

    @GetMapping("search/code")
    public WebRespResult searchCode(@RequestParam String keyword){
        try {
            Map<String, List> searchResult = searchService.searchCode(keyword);
            return new WebRespResult<Map>(200, "success", searchResult);
        } catch (Exception e){
            return new WebRespResult(500, "error");
        }

    }
}
