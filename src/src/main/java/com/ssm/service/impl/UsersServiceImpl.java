package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.mapper.UsersMapper;
import com.ssm.model.Users;
import com.ssm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service("usersService")
public class UsersServiceImpl extends BaseService<Users> implements UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public List<Users> selectByCountry(Users users, int page, int rows) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(users.getLoginid())) {
            criteria.andLike("username", "%" + users.getLoginid() + "%");
        }
        if (StringUtil.isNotEmpty(users.getLoginpwd())) {
            criteria.andLike("userpwd", "%" + users.getLoginpwd() + "%");
        }
        if (users.getId() != null) {
            criteria.andEqualTo("id", users.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

    @Override
    public Users login(String username, String pswd) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginid", username);
        List<Users> usersList = selectByExample(example);
        Users user = usersList.get(0);
        if(user.getLoginpwd().equals(pswd)) {
            return user;
        }
        else {
            return null;
        }
    }

    public int insertnoid(Users users) {
        int flag = usersMapper.insertnoid(users);
        return flag;
    }

    public List<Users> selectFPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        return selectAll();
    }
}
