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
 * @Notes EmailUserService
 * @date 2022/4/21
 * @time 3:17 PM
 * @example
 * @link
 */
@Service
public class EmailUserService implements UserDetailsService {
    /***
     * 提示语
     **/
    private final static String EMAIL_NOT_FOUND =
            "用户邮箱 %s 不存在";

    /***
     * userMapper
     **/
    @Resource
    private UserMapper userMapper;

    /***
     * 根据email获取到用户信息
     **/
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = new LambdaQueryChainWrapper<User>(userMapper)
                .eq(User::getEmail,email)
                .one();

        if (user == null) {
            throw new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND,email));
        }

        return user;
    }
}
