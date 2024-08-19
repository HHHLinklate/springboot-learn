package com.linklate.learn.springboot;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.linklate.learn.springboot.mapper.UserMapper;
import com.linklate.learn.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusUpdateQueryWrapperTest {


    @Autowired
    private UserMapper userMapper;

    /**
     * 使用queryWrapper
     * 注意：使用queryWrapper + 实体类形式可以实现修改，但是无法将列值修改为null值！
     */
    @Test
    public void test01() {
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
     * 使用updateWrapper
     * 使用updateWrapper可以随意设置列的值！！
     */
    @Test
    public void testQuick2(){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        //将id = 3 的email设置为null, age = 18
        updateWrapper.eq("id",3)
                .set("email",null)  // set 指定列和结果
                .set("age",18);
        //如果使用updateWrapper 实体对象写null即可!
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result = " + result);

    }



}
