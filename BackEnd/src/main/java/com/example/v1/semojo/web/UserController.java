package com.example.v1.semojo.web;

import com.example.v1.semojo.api.WebRespResult;
import com.example.v1.semojo.api.model.UserAuth;
import com.example.v1.semojo.api.model.UserInfo;
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

    @RequestMapping(value = "/register", method = POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
    public WebRespResult register(String username,
                                  String password,
                                  String confirmPassword,
                                  String email){

        return null;
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
