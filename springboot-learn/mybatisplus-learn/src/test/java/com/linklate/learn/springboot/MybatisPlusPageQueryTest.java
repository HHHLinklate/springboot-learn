package com.linklate.learn.springboot;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linklate.learn.springboot.mapper.UserMapper;
import com.linklate.learn.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusPageQueryTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage() {
        //IPage -> Page
        Page<User> page = new Page<>(1, 3);

        Page<User> userPage = userMapper.selectPage(page, null);

        //结果 page最后也会被封装结果
        long current = page.getCurrent();//页码
        long size = page.getSize(); //页容量
        List<User> records = page.getRecords();// 总条数
        long total = page.getTotal(); //总条数


    }

    @Test
    public void testMyPage() {
        Page<User> page = new Page<>(1, 3);
        userMapper.queryByAge(page, 1);

        long current = page.getCurrent();//页码
        System.out.println("current = " + current);
        long size = page.getSize(); //页容量
        System.out.println("size = "+ size);
        List<User> records = page.getRecords();//当前页的数据
        long total = page.getTotal(); //总条数
        System.out.println("total = " + total);
    }

}
