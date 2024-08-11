package com.linklate.learn.springboot.controller;


import com.linklate.learn.springboot.mapper.UserMapper;
import com.linklate.learn.springboot.pojo.User;
import com.linklate.learn.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("list")
    public List<User> list() {
        String sql = "select uid, username as userName, user_pwd as userPwd from sys_user;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @GetMapping("query")
    public List<User> query() {
        return userMapper.queryAll();

    }

    /**
     * 事务回滚
     */
    @GetMapping("delete")
    public void delete() {
        userService.delete();
    }
}
