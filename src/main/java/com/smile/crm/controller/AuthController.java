package com.smile.crm.controller;

import com.smile.crm.entity.User;
import com.smile.crm.mapper.UserMapper;
import com.smile.crm.model.service.IUserService;
import com.smile.crm.util.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    private IUserService iUserService;

    @GetMapping("/account/login")
    public ApiResponse<List> AccountLogin() {
        List<User> users = iUserService.getBaseMapper().selectList(null);

        return ApiResponse.success("success",200,users);
    }
}
