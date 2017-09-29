package com.ssm.test.unit;

import com.ssm.model.User;
import com.ssm.model.Users;
import com.ssm.service.UserService;
import com.ssm.service.UsersService;
import com.ssm.service.impl.UsersServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by chenkaihua on 15-9-15.
 */
public class UserServiceTest extends  BaseUnitTest{



    @Autowired
    UsersServiceImpl userService;


    @Test
    public void test(){
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
    }

}
