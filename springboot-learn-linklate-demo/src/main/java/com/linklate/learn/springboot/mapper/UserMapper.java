package com.linklate.learn.springboot.mapper;


import com.linklate.learn.springboot.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> queryAll();

    int delete(int i);
}
