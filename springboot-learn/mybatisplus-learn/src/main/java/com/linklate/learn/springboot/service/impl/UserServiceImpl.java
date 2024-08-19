package com.linklate.learn.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linklate.learn.springboot.mapper.UserMapper;
import com.linklate.learn.springboot.pojo.User;
import com.linklate.learn.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
