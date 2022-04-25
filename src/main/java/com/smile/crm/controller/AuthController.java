package com.smile.crm.controller;

import com.smile.crm.model.service.IUserService;
import com.smile.crm.request.AccountLoginRequest;
import com.smile.crm.request.EmailLoginRequest;
import com.smile.crm.service.UserService;
import com.smile.crm.util.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author smile
 * @Notes 用户和权限
 * @date 2022/4/13
 * @time 12:09 PM
 * @example
 * @link
 */
@RestController
public class AuthController {
    /***
     * 业务service
     **/
    @Resource
    private UserService userService;

    /***
     * 数据service
     **/
    @Resource
    private IUserService iUserService;

    /***
     * @Notes email登陆
     * @param emailLoginRequest  email
     * @return com.smile.crm.util.response.ApiResponse
     * @author smile
     * @date 2022/4/22
     * @time 12:09 PM
     **/
    @PostMapping("/email/login")
    public ApiResponse emailLogin(@RequestBody EmailLoginRequest emailLoginRequest) {
        return userService.emailLogin(emailLoginRequest.getEmail());
    }

    /***
     * @Notes 账号登陆
     * @param accountLoginRequest username,password
     * @return com.smile.crm.util.response.ApiResponse
     * @author smile
     * @date 2022/4/22
     * @time 12:09 PM
     **/
    @PostMapping("/account/login")
    public ApiResponse accountLogin(@RequestBody AccountLoginRequest accountLoginRequest) {
        return userService.accountLogin(accountLoginRequest.getUsername()
                ,accountLoginRequest.getPassword());
    }

    @GetMapping("/user/{userId}")
    public String user(@PathVariable String userId) {
        return "smile";
    }
}
