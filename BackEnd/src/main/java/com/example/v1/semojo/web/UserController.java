package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.enums.UserResultEnum;
import com.example.v1.semojo.api.model.*;
import com.example.v1.semojo.api.util.UserRespResultUtil;
import com.example.v1.semojo.entities.Product;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import com.example.v1.semojo.services.UserService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

/*
@ApiOperation，整个接口属性配置：
　　　　value：接口说明，展示在接口列表。
　　　　notes：接口详细说明，展示在接口的详情页。
　　　　tags：接口的标签，相同标签的接口会在一个标签页下展示。
　　　　httpMethod：支持的HTTP的方法。
@ApiImplicitParam，请求参数属性配置：
　　　　name：参数名称
　　　　value：参数说明
　　　　required：是否必须
　　　　dataType：数据类型　　
@ApiResponse，返回结果属性配置：
　　　　code：返回结果的编码。
　　　　message：返回结果的说明。
　　　　response：返回结果对应的类。　　　　
 */


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "register a user")
    @ApiResponses({
            @ApiResponse(code=200, message="success", response= UserAuthModel.class),
    })
    public WebRespResult register(@RequestBody RegisterModel model){
//        try {
//            User user = userService.saveUser(username, password, confirmPassword, email);
//            UserAuth auth = user.getAuth();
//            StringBuffer roleStrBuf = new StringBuffer();
//            for (GrantedAuthority authority: auth.getAuthorities()){
//                roleStrBuf.append(authority.getAuthority()).append(",");
//            }
//            UserAuthModel userAuthModel = new UserAuthModel(user.getId(), auth.getUsername(), roleStrBuf.toString());
//            return UserRespResultUtil.success(userAuthModel);
//        }catch (Exception e){
//            e.printStackTrace();
//            if (e.getMessage().equals("password matching is not the same")){
//                return UserRespResultUtil.error(400, "Wrong Matching");
//            }
//        }
//        return UserRespResultUtil.error(500, "Unknown Exception");
        String username = model.getUsername();
        String email = model.getEmail();
        String password = model.getPassword();
        String confirmPassword = model.getConfirmPassword();

        if (userService.findUserByUsername(username) != null || userService.findUserByEmail(email) != null){
            return UserRespResultUtil.error(UserResultEnum.USER_IS_EXISTS.getCode(), UserResultEnum.USER_IS_EXISTS.getMsg());
        }else if(!password.equals(confirmPassword)){
            return UserRespResultUtil.error(UserResultEnum.CONFIRM_PASSWORD_MISMATCH.getCode(), UserResultEnum.CONFIRM_PASSWORD_MISMATCH.getMsg());
        }else {
            userService.saveUser(username, password, email);
            User n_user = userService.findUserByUsername(username);
            UserAuth auth = n_user.getAuth();
            StringBuffer roleStrBuf = new StringBuffer();
            for (GrantedAuthority authority: auth.getAuthorities()){
                roleStrBuf.append(authority.getAuthority()).append(",");
            }
            UserAuthModel userAuthModel = new UserAuthModel(n_user.getUserId(), auth.getUsername(), roleStrBuf.toString());
            return UserRespResultUtil.success(userAuthModel);
        }
        // TODO: 2020/11/22 add judgement
    }

    @RequestMapping(value = "/info/{username}", method = PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "updateInfo", notes = "update the Info", httpMethod = "PUT")
    public WebRespResult updateInfo(@PathVariable String username,
                                    @RequestBody UserInfoModel userInfoModel){
        if (userService.findUserByUsername(username) == null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else {
            userService.updateUser(username, userInfoModel);
            return UserRespResultUtil.success(userInfoModel);
        }
    }

    @RequestMapping(value = "/admin/users", method = GET)
    @ApiOperation(value = "get the list", notes = "obtain the list of registered user", tags = "admin", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code=200, message="success", response= List.class)
    })
    public WebRespResult<List<UserAllInfoModel>> getUserList(long limit, long start){
        List<UserAllInfoModel> userList = userService.getUserList(limit, start);
        return UserRespResultUtil.success(userList);
    }

    @RequestMapping(value = "admin/user/{username}", method = DELETE)
    @ApiOperation(value = "delete user", notes = "delete user by id", tags = "admin", httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code=200, message="success", response= WebRespResult.class),
    })
    public WebRespResult deleteUser(@PathVariable String username){
        if (userService.findUserByUsername(username) == null){
            return UserRespResultUtil.error(UserResultEnum.USER_NOT_EXIST.getCode(), UserResultEnum.USER_NOT_EXIST.getMsg());
        }else {
            userService.deleteUserByUserName(username);
            return UserRespResultUtil.success(UserResultEnum.SUCCESS.getCode(), UserResultEnum.SUCCESS.getMsg());
        }
    }

    @GetMapping("/customer/{username}/portrait")
    public WebRespResult getPortrait(@PathVariable String username){
        try {
            String path = userService.getPortrait(username);
            return new WebRespResult<>(200, "success", path);
        }catch (Exception e){
            e.printStackTrace();
            return new WebRespResult(400, e.getMessage());
        }
    }

    @GetMapping("/customer/{username}/userpage/info")
    public WebRespResult getUserInfo(@PathVariable String username){
        try{
            UserInfoModel info = userService.getUserInfo(username);
            return new WebRespResult<>(200, "success", info);
        }catch (NullPointerException e){
            return new WebRespResult<>(400, "not such user");
        }catch (Exception e){
            return new WebRespResult<>(500, "unknown error");
        }
    }

    @GetMapping("/customer/{username}/userpage/products")
    public WebRespResult getPurchasedProduct(@PathVariable String username){
        try{
            List<Product> products = userService.getPurchasedProduct(username);
            List<ProductPreviewModel> productPreviewModels = new ArrayList<>();
            for (Product product: products){
                productPreviewModels.add(new ProductPreviewModel(product));
            }
            return new WebRespResult<>(200, "success", productPreviewModels);
        }catch (NullPointerException e){
            return new WebRespResult<>(400, "not such user");
        }catch (Exception e){
            return new WebRespResult<>(500, "unknown error");
        }
    }

    @GetMapping("/admin/{username}/userpage/authlist")
    public WebRespResult getNeededAuthUserList(){
        try{
            List<UserAuth> neededAuthUsers = userService.getNeededAuthUsers();
            for (UserAuth auth: neededAuthUsers){
                auth.setUser(null);
            }
            return new WebRespResult<List<UserAuth>>(200, "success", neededAuthUsers);
        }catch (NullPointerException e){
            return new WebRespResult<>(400, "not such user");
        }catch (Exception e){
            return new WebRespResult<>(500, "unknown error");
        }
    }

    @PutMapping("/customer/{username}/userpage/auth")
    public WebRespResult askAuthAsContributor(@PathVariable String username){
        try{
            userService.updateUserRole(username, 1000);
            return new WebRespResult(200, "success");
        }catch (NullPointerException e){
            return new WebRespResult<>(400, "not such user");
        }catch (Exception e){
            return new WebRespResult<>(500, "unknown error");
        }
    }

    @PutMapping("/admin/{username}/userpage/auth")
    public WebRespResult improveAuth(@PathVariable String username,
                                     @RequestParam String requiredUser,
                                     @RequestParam String updateType){
        try{
            switch (updateType) {
                case "contributor": userService.updateUserRole(requiredUser, 2);
                    return new WebRespResult(200, "success");
                case "admin": userService.updateUserRole(requiredUser, 3);
                    return new WebRespResult(200, "success");
                case "customer": userService.updateUserRole(requiredUser, 1);
                default: return new WebRespResult(400, "Wrong Role Type");
            }
        }catch (NullPointerException e){
            return new WebRespResult<>(400, "not such user");
        }catch (Exception e){
            return new WebRespResult<>(500, "unknown error");
        }
    }

    @GetMapping("/contributor/{username}/userpage/contributed_products")
    public WebRespResult getContributedProduct(@PathVariable String username){
            List<Product> contributedProducts = userService.getContributedProducts(username);
            List<ProductPreviewModel> productPreviewModels = new ArrayList<>();
//            System.out.println(contributedProducts.size() + "-------------------");
            if (!contributedProducts.isEmpty()) {
                for (Product product : contributedProducts) {
                    if (product != null) {
                        productPreviewModels.add(new ProductPreviewModel(product));
                    }
                }
            }
            return new WebRespResult<>(200, "success", productPreviewModels);

    }
}
