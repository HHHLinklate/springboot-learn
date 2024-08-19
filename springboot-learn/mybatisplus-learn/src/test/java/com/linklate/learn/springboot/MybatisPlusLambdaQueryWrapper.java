package com.linklate.learn.springboot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklate.learn.springboot.mapper.UserMapper;
import com.linklate.learn.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusLambdaQueryWrapper {

    @Autowired
    private UserMapper userMapper;


    /**
     * 对比QueryWrapper 和 LambdaQueryWrapper
     */
    public void test_01(){
        //QueryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "John")
                .ge("age", 18)
                .orderByDesc("create_time")
                .last("limit 10");
        List<User> userList = userMapper.selectList(queryWrapper);

        // LambdaQueryWrapper
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(User::getName, "John")
                .ge(User::getAge, 18)
                .orderByDesc(User::getAge)
                .last("limit 10");
        List<User> userList1 = userMapper.selectList(lambdaQueryWrapper);
    }
}
