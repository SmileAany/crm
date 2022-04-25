package com.smile.crm.model.service.impl;

import com.smile.crm.entity.User;
import com.smile.crm.mapper.UserMapper;
import com.smile.crm.model.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
