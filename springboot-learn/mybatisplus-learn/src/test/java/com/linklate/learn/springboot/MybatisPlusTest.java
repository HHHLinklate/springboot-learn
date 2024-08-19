package com.linklate.learn.springboot;

import com.linklate.learn.springboot.mapper.UserMapper;
import com.linklate.learn.springboot.pojo.User;
import com.linklate.learn.springboot.service.UserService;
import org.apache.catalina.util.ToStringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(18);
        user.setName("hehehe");
        user.setEmail("XXXXXXXXXX");
        //baseMapper提供的数据库插入方法
        int row = userMapper.insert(user);
    }

    @Test
    public void testDelete() {
        //根据id删除
        int rows = userMapper.deleteById(1822610541323300865L);
        System.out.println("rows = " + rows);

        //根据age=20
        Map param = new HashMap<>();
        param.put("age", 20);
        int i = userMapper.deleteByMap(param);
        System.out.println("i = " + i);

        //wrapper 条件封装对象，无限的封装条件
        // userMapper.delete(wrapper);
    }

    @Test
    public void testUpdate() {

        //TODO: update 当属性值为null的时候，不修改！

        //updateById 实体id必须有值！
        //user_id = 1 age改为30
        User user = new User();
        user.setId(1L);
        user.setAge(30);
        //update user set age=30 where id=1
        int i = userMapper.updateById(user);
        System.out.println("i=" + i);

        //将所有人的年龄改为22
        User user1 = new User();
        user1.setAge(22);
        int update = userMapper.update(user, null);//null 没条件
        System.out.println("update = " + update);
    }


    @Test
    public void testSelect() {
        userMapper.selectById(1);

        //ids集合查询
        List<Long> ids = new ArrayList<>();
        ids.add(1L); ids.add(2L);
        List<User> users = userMapper.selectBatchIds(ids);
        System.out.println("users = " +users);
    }

    @Test
    public void testSave() {

        List<User> list = new ArrayList<>();
        User user = new User();
        user.setAge(18);
        user.setEmail("jj");
        user.setName("驴蛋蛋");
        list.add(user);

        User user1 = new User();
        user1.setAge(18);
        user1.setEmail("jj");
        user1.setName("狗剩子");
        list.add(user1);

        boolean b = userService.saveBatch(list);
        System.out.println("b= " + b);

    }


    @Test
    public void testSaveOrUpdate() {

        User user = new User();
        user.setId(1822618888705458177L);
        user.setAge(18);
        user.setEmail("jj");
        user.setName("驴蛋蛋2");
        userService.saveOrUpdate(user);
    }

    @Test
    public void testRemove() {

        boolean b = userService.removeById(1822618144602341378L);
        System.out.println("b = " + b);
    }

    @Test
    public void testUpdate2() {
        User user = new User();
        user.setId(1822618888705458177L);
        user.setAge(18);
        user.setEmail("jjj");
        user.setName("驴蛋蛋22");
        boolean b = userService.updateById(user);
        System.out.println("b = " + b);
    }

    @Test
    public void testGetOrList() {
        User byId = userService.getById(1L); //get返回的是单个对象

        List<User> list = userService.list(null); //查询全部，返回的集合


        User user = new User();
        user.setId(1822618888705458177L);
        user.setAge(18);
        user.setEmail("jjj");
        user.setName("驴蛋蛋22");
        boolean b = userService.updateById(user);
        System.out.println("b = " + b);
    }
}
