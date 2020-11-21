package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.UserAuth;
import com.example.v1.semojo.api.model.UserInfo;
import com.example.v1.semojo.api.model.UserAuthModel;
import com.example.v1.semojo.api.model.UserInfoModel;
import com.example.v1.semojo.api.util.UserRespResultUtil;
import com.example.v1.semojo.entities.User;
import com.example.v1.semojo.entities.UserAuth;
import com.example.v1.semojo.entities.UserInfo;
import com.example.v1.semojo.services.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "account", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String"),
            @ApiImplicitParam(name = "confirmPassword", value = "string matching password", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200, message="success", response= UserAuth.class),
    })
    public WebRespResult register(String username, String password,
                                  String confirmPassword, String email
                                  ){
        User n_user = new User();
        System.out.println("**********************");
        n_user.setName("");
        UserAuth n_auth = new UserAuth();
        n_auth.setUser(n_user);
        n_auth.setUsername(username);
        n_auth.setPassword(password);
        n_auth.setAccountNonExpired(true);
        n_auth.setAccountNonLocked(true);
        n_auth.setEnabled(true);
        n_auth.setCredentialsNonExpired(true);
        n_auth.setRole(1);
        n_user.setAuth(n_auth);
        System.out.println("---------");
        UserInfo n_info = new UserInfo();
        n_info.setUser(n_user);
        n_info.setAddress("");
        n_info.setEmail(email);
        n_info.setGender("");
        n_info.setPhoneNum("");
        n_info.setPortrait("");
        n_info.setQqNum("");
        n_info.setWeChatNum("");
        n_user.setInfo(n_info);
        UserAuthModel userAuthModel = new UserAuthModel(1, username);
        System.out.println("+++++++++++++++");
        return UserRespResultUtil.success(userAuthModel);
    }

    @RequestMapping(value = "/info/{userId}", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "updateInfo", notes = "update the Info", httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "token", value = "jwt", required = true, dataType = "bear token")
    )
    public WebRespResult updateInfo(@PathVariable Long userId,
                                    @RequestBody UserInfo userInfo){

        return null;
    }

    @RequestMapping(value = "/admin/users", method = GET)
    @ApiOperation(value = "get the list", notes = "obtain the list of registered user", tags = "admin", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "start index in database", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "the number of user in list", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "jwt", required = true, dataType = "bear token")
    })
    @ApiResponses({
            @ApiResponse(code=200, message="success", response= List.class)
    })
    public WebRespResult<List<UserInfo>> getUserList(){
        return null;
    }

    @RequestMapping(value = "admin/user/{userId}", method = DELETE)
    @ApiOperation(value = "delete user", notes = "delete user by id", tags = "admin", httpMethod = "DELETE")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "token", value = "jwt", required = true, dataType = "bear token")
    )
    @ApiResponses({
            @ApiResponse(code=200, message="success", response= WebRespResult.class),
    })
    public WebRespResult deleteUser(@PathVariable Integer userId){
        return null;
    }
}
