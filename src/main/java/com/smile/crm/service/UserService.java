package com.smile.crm.service;

import com.smile.crm.entity.User;
import com.smile.crm.util.response.ApiResponse;

import java.util.HashMap;

/**
 * @author smile
 * @Notes
 * @date 2022/4/21
 * @time 4:28 PM
 * @example
 * @link
 */
public interface UserService {
    ApiResponse emailLogin(String email);

    ApiResponse accountLogin(String username,String password);

    HashMap<String,Object> getLoginReturn(User user);
}
