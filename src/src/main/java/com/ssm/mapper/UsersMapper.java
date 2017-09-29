package com.ssm.mapper;

import com.ssm.model.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value = "usersMapper")
public interface UsersMapper extends Mapper<Users> {
    int insertnoid(Users users);
}