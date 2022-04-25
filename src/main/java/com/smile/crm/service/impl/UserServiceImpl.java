package com.smile.crm.service.impl;

import com.smile.crm.entity.User;
import com.smile.crm.property.JwtProperty;

import com.smile.crm.security.token.AccountAuthenticationToken;
import com.smile.crm.security.token.EmailAuthenticationToken;
import com.smile.crm.service.UserService;
import com.smile.crm.util.jwt.Jwt;
import com.smile.crm.util.response.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author smile
 * @Notes
 * @date 2022/4/21
 * @time 4:28 PM
 * @example
 * @link
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private AuthenticationManager authenticationManager;

    /***
     * jwt 自定义配置值
     **/
    @Resource
    private JwtProperty jwtProperty;

    /***
     * 封装登陆成功的数据
     **/
    public HashMap<String,Object> getLoginReturn(User user) {
        HashMap<String,String> claims = new HashMap<>();
        claims.put("userId", String.valueOf(user.getId()));

        String token = jwtProperty.getPrefix() + StringUtils.SPACE + Jwt.token(claims);

        HashMap<String,Object> data = new HashMap<>();
        data.put("token",token);

        return data;
    }

    /***
     * 邮箱登陆
     **/
    @Override
    public ApiResponse emailLogin(String email) {
        Authentication authentication =
                authenticationManager.authenticate(new EmailAuthenticationToken(email));

        User user = (User) authentication.getPrincipal();

        return ApiResponse.success("success",200,getLoginReturn(user));
    }

    /***
     * 账号登陆
     **/
    @Override
    public ApiResponse accountLogin(String username, String password) {
        Authentication authentication =
                authenticationManager.authenticate(new AccountAuthenticationToken(username,password));

        User user = (User) authentication.getPrincipal();

        return ApiResponse.success("success",200,getLoginReturn(user));
    }
}
