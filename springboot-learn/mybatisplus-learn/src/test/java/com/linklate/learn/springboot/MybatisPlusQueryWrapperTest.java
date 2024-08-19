package com.linklate.learn.springboot;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linklate.learn.springboot.mapper.UserMapper;
import com.linklate.learn.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusQueryWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_01() {
        //查询用户名包含a, 年龄在20-30之间，并且邮箱不为null的用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        // 链式调用
        userQueryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        // select * from user where name like "%a%" and age >= 20 and age <=30 and email is not null
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);
    }

    @Test
    public void test02() {
        //按年龄降序查询用户，如果年龄相同则按id升序排列
        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03() {
        //删除email为空的用户
        //DELETE FROM t_user WHERE (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        //条件构造器也可以构建删除语句的条件
        int result = userMapper.delete(queryWrapper);
        System.out.println("受影响的行数：" + result);
    }


    /**
     * and和or关键字使用(修改)
     */
    @Test
    public void test04() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //将年龄大于20并且用户名中包含有a或邮箱为null的用户信息修改
        //UPDATE t_user SET age=?, email=? WHERE username LIKE ? AND age > ? OR email IS NULL)
        queryWrapper
                .like("username", "a")
                .gt("age", 20)
                .or()
                .isNull("email");
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    /**
     * 指定列映射查询
     */
    @Test
    public void test05() {
        //查询用户信息的username和age字段
        //SELECT username,age FROM t_user
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "age");
        //selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * condition判断组织条件
     */
    @Test
    public void test06() {
        String name = "root";
        int age = 18;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //判断条件拼接
        //当name不为null拼接等于, age > 1 拼接等于判断
        //方案1: 手动判断
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.eq("name", name);
        }
        if (age > 1) {
            queryWrapper.eq("age", age);
        }

        //方案2: 拼接condition判断
        //每个条件拼接方法都condition参数,这是一个比较运算,为true追加当前条件!
        //eq(condition,列名,值)
        queryWrapper.eq(!StringUtils.isEmpty(name), "name", name)
                .eq(age > 1, "age", age);
    }




}
