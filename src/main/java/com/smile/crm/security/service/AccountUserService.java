package com.smile.crm.security.service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.smile.crm.entity.User;
import com.smile.crm.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author smile
 * @Notes AccountUserService
 * @date 2022/4/22
 * @time 12:23 PM
 * @example
 * @link
 */
@Service
public class AccountUserService implements UserDetailsService {
    /***
     * 提示语
     **/
    private final static String USER_NOT_FOUND =
            "账号 %s 不存在";

    /***
     * userMapper
     **/
    @Resource
    private UserMapper userMapper;

    /***
     * 根据用户名获取到用户信息
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new LambdaQueryChainWrapper<User>(userMapper)
                .eq(User::getUsername,username)
                .one();

        if (user == null) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND,username));
        }

        return user;
    }
}
