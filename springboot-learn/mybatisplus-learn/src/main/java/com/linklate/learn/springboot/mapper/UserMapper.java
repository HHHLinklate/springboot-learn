package com.linklate.learn.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.linklate.learn.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * mybatis: 1.mapper接口 crud方法  2. mapperxml curd sql语句
 * mybatis-plus：1. mapper接口 extends BaseMapper 2.curd方法 curd的sql
 *
 */
public interface UserMapper extends BaseMapper<User> {

    //定义一个根据年龄参数插叙你，并且分页的方法 age > xx
    IPage<User> queryByAge(IPage<User> page, @Param("age") Integer age);
}
