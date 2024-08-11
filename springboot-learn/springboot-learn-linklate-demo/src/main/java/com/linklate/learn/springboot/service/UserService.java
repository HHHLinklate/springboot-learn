package com.linklate.learn.springboot.service;

import com.linklate.learn.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void delete() {
        int rows = userMapper.delete(1);
        System.out.println("rows = " + rows);
        int i = rows/0;
    }
}
