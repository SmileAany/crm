package com.smle.crm.controller;

import com.alibaba.fastjson.JSON;
import com.smle.crm.util.response.ApiResponse;
import com.smle.crm.util.response.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/account/login")
    public ApiResponse AccountLogin() {
        return ApiResponse.success();
    }
}
